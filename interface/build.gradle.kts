import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    `maven-publish`
    id("org.openapi.generator") version "7.12.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-web")
    compileOnly("jakarta.servlet:jakarta.servlet-api")
}

val openapiSpecFilename = "tennis-game.yaml"
val openapiSpecFile: Provider<RegularFile> =
    layout.buildDirectory.file("resources/main/META-INF/resources/$openapiSpecFilename")
val generatedSourcesDir: Provider<Directory> =
    layout.buildDirectory.dir("generated-sources")

sourceSets {
    getByName("main") {
        java {
            srcDir(
                generatedSourcesDir
                    .get()
                    .dir("src/main/java")
                    .asFile.absolutePath,
            )
        }
    }
}

tasks.register<GenerateTask>("openApiGenerateServer") {
    inputSpec.set(openapiSpecFile.get().asFile.absolutePath)
    dependsOn("processResources")
    apiPackage.set("com.tennis.game.infra.api")
    generatorName.set("spring")
    modelPackage.set("com.tennis.game.infra.api.model")
    modelNameSuffix.set("ApiDto")
    additionalProperties.set(
        mapOf(
            "annotationLibrary" to "none",
            "documentationProvider" to "none",
            "serializationLibrary" to "jackson",
            "hideGenerationTimestamp" to "true",
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "useTags" to "true",
            "skipApiUtil" to true,
            "openApiNullable" to false,
        )
    )
    validateSpec.set(false)
}

tasks.register<Copy>("copyOpenApiSpec") {
    from("$projectDir/src/main/spec/tennis-game.yaml")
    into(layout.buildDirectory.dir("resources/main/META-INF/resources"))
}

tasks.named("processResources") {
    dependsOn("copyOpenApiSpec")
}

// run openApiGenerate before compilation
tasks.getByName("compileJava") {
    dependsOn("openApiGenerateServer")
}
