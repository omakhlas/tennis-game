plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.tennis.game"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

subprojects {
	apply {
		plugin("java")
		plugin("io.spring.dependency-management")
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}")
		}
	}

	dependencies {
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		implementation("org.springframework.boot:spring-boot-starter-logging")
	}

	tasks.withType<Test>() {
		useJUnitPlatform()
	}

	tasks.withType<JavaCompile> {
		sourceCompatibility = Versions.java
		targetCompatibility = Versions.java
	}


}
