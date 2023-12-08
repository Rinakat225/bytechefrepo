dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation(libs.com.github.mizosoft.methanol)
    implementation("org.apache.commons:commons-lang3")
    implementation("org.slf4j:slf4j-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.boot:spring-boot")
    implementation(project(":server:libs:atlas:atlas-coordinator:atlas-coordinator-api"))
    implementation(project(":server:libs:atlas:atlas-worker:atlas-worker-api"))
    implementation(project(":server:libs:core:commons:commons-util"))
    implementation(project(":server:libs:core:data-storage:data-storage-api"))
    implementation(project(":server:libs:core:file-storage:file-storage-api"))
    implementation(project(":server:libs:hermes:hermes-component:hermes-component-registry:hermes-component-registry-api"))
    implementation(project(":server:libs:hermes:hermes-connection:hermes-connection-api"))
    implementation(project(":server:libs:hermes:hermes-coordinator:hermes-coordinator-api"))
    implementation(project(":server:libs:hermes:hermes-execution:hermes-execution-api"))
    implementation(project(":server:libs:hermes:hermes-worker:hermes-worker-api"))

    testImplementation("org.springframework.data:spring-data-jdbc")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    testImplementation(libs.org.wiremock.wiremock)
    testImplementation(project(":server:libs:atlas:atlas-file-storage:atlas-file-storage-service"))
    testImplementation(project(":server:libs:core:encryption:encryption-impl"))
    testImplementation(project(":server:libs:core:commons:commons-data"))
    testImplementation(project(":server:libs:core:tag:tag-service"))
    testImplementation(project(":server:libs:core:file-storage:file-storage-base64-service"))
    testImplementation(project(":server:libs:core:liquibase-config"))
    testImplementation(project(":server:libs:hermes:hermes-configuration:hermes-configuration-instance-api"))
    testImplementation(project(":server:libs:hermes:hermes-component:hermes-component-registry:hermes-component-registry-service"))
    testImplementation(project(":server:libs:hermes:hermes-connection:hermes-connection-service"))
    testImplementation(project(":server:libs:hermes:hermes-oauth2:hermes-oauth2-api"))
    testImplementation(project(":server:libs:modules:components:petstore"))
    testImplementation(project(":server:libs:test:test-int-support"))
}
