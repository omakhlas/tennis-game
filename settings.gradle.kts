plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "tennis-game"
include("domain")
include("infrastructure")
include("interface")
