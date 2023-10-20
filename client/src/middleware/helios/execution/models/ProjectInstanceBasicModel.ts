/* tslint:disable */
/* eslint-disable */
/**
 * Automation Execution API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * Contains configurations and connections required for the execution of project workflows.
 * @export
 * @interface ProjectInstanceBasicModel
 */
export interface ProjectInstanceBasicModel {
    /**
     * The description of a project instance.
     * @type {string}
     * @memberof ProjectInstanceBasicModel
     */
    description?: string;
    /**
     * The created by.
     * @type {string}
     * @memberof ProjectInstanceBasicModel
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof ProjectInstanceBasicModel
     */
    readonly createdDate?: Date;
    /**
     * The id of a project instance.
     * @type {number}
     * @memberof ProjectInstanceBasicModel
     */
    readonly id?: number;
    /**
     * The last execution date.
     * @type {string}
     * @memberof ProjectInstanceBasicModel
     */
    readonly lastExecutionDate?: string;
    /**
     * The last modified by.
     * @type {string}
     * @memberof ProjectInstanceBasicModel
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof ProjectInstanceBasicModel
     */
    readonly lastModifiedDate?: Date;
    /**
     * The name of a project instance.
     * @type {string}
     * @memberof ProjectInstanceBasicModel
     */
    name: string;
    /**
     * Th id of a project.
     * @type {number}
     * @memberof ProjectInstanceBasicModel
     */
    projectId?: number;
    /**
     * If a workflow is enabled or not in the project instance.
     * @type {boolean}
     * @memberof ProjectInstanceBasicModel
     */
    enabled?: boolean;
}

/**
 * Check if a given object implements the ProjectInstanceBasicModel interface.
 */
export function instanceOfProjectInstanceBasicModel(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "name" in value;

    return isInstance;
}

export function ProjectInstanceBasicModelFromJSON(json: any): ProjectInstanceBasicModel {
    return ProjectInstanceBasicModelFromJSONTyped(json, false);
}

export function ProjectInstanceBasicModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): ProjectInstanceBasicModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'description': !exists(json, 'description') ? undefined : json['description'],
        'createdBy': !exists(json, 'createdBy') ? undefined : json['createdBy'],
        'createdDate': !exists(json, 'createdDate') ? undefined : (new Date(json['createdDate'])),
        'id': !exists(json, 'id') ? undefined : json['id'],
        'lastExecutionDate': !exists(json, 'lastExecutionDate') ? undefined : json['lastExecutionDate'],
        'lastModifiedBy': !exists(json, 'lastModifiedBy') ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': !exists(json, 'lastModifiedDate') ? undefined : (new Date(json['lastModifiedDate'])),
        'name': json['name'],
        'projectId': !exists(json, 'projectId') ? undefined : json['projectId'],
        'enabled': !exists(json, 'enabled') ? undefined : json['enabled'],
    };
}

export function ProjectInstanceBasicModelToJSON(value?: ProjectInstanceBasicModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'description': value.description,
        'name': value.name,
        'projectId': value.projectId,
        'enabled': value.enabled,
    };
}

