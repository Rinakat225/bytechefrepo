
/*
 * Copyright 2021 <your company/name>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.task.dispatcher.loop;

import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.ITEM;
import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.ITEM_INDEX;
import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.ITERATEE;
import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.LIST;
import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.LOOP;
import static com.bytechef.task.dispatcher.loop.constant.LoopTaskDispatcherConstants.LOOP_FOREVER;

import com.bytechef.atlas.execution.domain.Context;
import com.bytechef.atlas.execution.domain.TaskExecution;
import com.bytechef.atlas.file.storage.facade.WorkflowFileStorageFacade;
import com.bytechef.atlas.execution.message.broker.TaskMessageRoute;
import com.bytechef.message.broker.MessageBroker;
import com.bytechef.atlas.execution.service.ContextService;
import com.bytechef.atlas.execution.service.TaskExecutionService;
import com.bytechef.atlas.configuration.task.Task;
import com.bytechef.atlas.configuration.task.WorkflowTask;
import com.bytechef.atlas.coordinator.task.dispatcher.TaskDispatcher;
import com.bytechef.atlas.coordinator.task.dispatcher.TaskDispatcherResolver;
import com.bytechef.commons.util.MapUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A {@link TaskDispatcher} implementation which implements a loop construct. The dispatcher works by executing the
 * <code>iteratee</code> function on each item on the <code>stream</code>.
 *
 * @author Ivica Cardic
 */
public class LoopTaskDispatcher implements TaskDispatcher<TaskExecution>, TaskDispatcherResolver {

    private final ContextService contextService;
    private final MessageBroker messageBroker;
    private final TaskDispatcher<? super Task> taskDispatcher;
    private final TaskExecutionService taskExecutionService;
    private final WorkflowFileStorageFacade workflowFileStorageFacade;

    @SuppressFBWarnings("EI")
    public LoopTaskDispatcher(
        ContextService contextService, MessageBroker messageBroker, TaskDispatcher<? super Task> taskDispatcher,
        TaskExecutionService taskExecutionService, WorkflowFileStorageFacade workflowFileStorageFacade) {

        this.contextService = contextService;
        this.taskDispatcher = taskDispatcher;
        this.messageBroker = messageBroker;
        this.taskExecutionService = taskExecutionService;
        this.workflowFileStorageFacade = workflowFileStorageFacade;
    }

    @Override
    @SuppressFBWarnings("NP")
    public void dispatch(TaskExecution taskExecution) {
        boolean loopForever = MapUtils.getBoolean(taskExecution.getParameters(), LOOP_FOREVER, false);
        WorkflowTask iteratee = MapUtils.getRequired(taskExecution.getParameters(), ITERATEE, WorkflowTask.class);
        List<?> list = MapUtils.getList(taskExecution.getParameters(), LIST, Collections.emptyList());

        taskExecution.setStartDate(LocalDateTime.now());
        taskExecution.setStatus(TaskExecution.Status.STARTED);

        taskExecution = taskExecutionService.update(taskExecution);

        if (loopForever || !list.isEmpty()) {
            TaskExecution subTaskExecution = TaskExecution.builder()
                .jobId(taskExecution.getJobId())
                .parentId(taskExecution.getId())
                .priority(taskExecution.getPriority())
                .taskNumber(1)
                .workflowTask(iteratee)
                .build();

            Map<String, Object> newContext = new HashMap<>(
                workflowFileStorageFacade.readContextValue(
                    contextService.peek(
                        Objects.requireNonNull(taskExecution.getId()), Context.Classname.TASK_EXECUTION)));

            WorkflowTask workflowTask = taskExecution.getWorkflowTask();

            Map<String, Object> workflowTaskNameMap = new HashMap<>();

            if (!list.isEmpty()) {
                workflowTaskNameMap.put(ITEM, list.get(0));
            }

            workflowTaskNameMap.put(ITEM_INDEX, 0);

            newContext.put(workflowTask.getName(), workflowTaskNameMap);

            subTaskExecution = taskExecutionService.create(subTaskExecution.evaluate(newContext));

            contextService.push(
                Objects.requireNonNull(
                    subTaskExecution.getId()),
                Context.Classname.TASK_EXECUTION,
                workflowFileStorageFacade.storeContextValue(
                    subTaskExecution.getId(), Context.Classname.TASK_EXECUTION, newContext));

            taskDispatcher.dispatch(subTaskExecution);
        } else {
            taskExecution.setStartDate(LocalDateTime.now());
            taskExecution.setEndDate(LocalDateTime.now());
            taskExecution.setExecutionTime(0);

            messageBroker.send(TaskMessageRoute.TASKS_COMPLETE, taskExecution);
        }
    }

    @Override
    public TaskDispatcher<? extends Task> resolve(Task task) {
        if (Objects.equals(task.getType(), LOOP + "/v1")) {
            return this;
        }

        return null;
    }
}
