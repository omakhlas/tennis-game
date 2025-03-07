object BuildSrcVersions {
    const val java = "21" // must be the same as Versions.java
    const val openapiGenerator = "7.7.0" // https://github.com/OpenAPITools/openapi-generator
}

plugins {
    java
    `kotlin-dsl`
    id("io.spring.dependency-management") version "1.1.7" // must be the same as Versions.springDependencyManagementPlugin
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools:openapi-generator-cli:${BuildSrcVersions.openapiGenerator}")
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = BuildSrcVersions.java
        targetCompatibility = BuildSrcVersions.java
    }
}
