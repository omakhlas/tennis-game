plugins {
    java
    `maven-publish`
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
        resources {
            srcDir("$projectDir/src/main/spec")
        }
    }
}

tasks.register<GenerateOpenApiServerTask>("openApiGenerateServer") {
    this.basePackage.set("com.tennis.game.infra.api")
    // this.specFile.set(openapiSpecFile.get().asFile)
    this.specFile.set(file("$projectDir/src/main/spec/tennis-game.yaml"))
    this.outputDir.set(generatedSourcesDir)
    dependsOn("processResources")
}

// run openApiGenerate before compilation
tasks.getByName("compileJava") {
    dependsOn("openApiGenerateServer")
}
