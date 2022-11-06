import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("com.vaadin") version "23.2.6"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { setUrl("https://maven.vaadin.com/vaadin-addons") }
}

// sudo chown -R $(whoami) ~/.npm
// https://stackoverflow.com/questions/16151018/how-to-fix-npm-throwing-error-without-sudo

extra["vaadinVersion"] = "23.2.6"

dependencies {

    compileOnly("org.springframework.boot:spring-boot-devtools")
    //implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.dhatim:fastexcel:0.14.0")
    implementation("org.dhatim:fastexcel-reader:0.14.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.vaadin:vaadin-spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

defaultTasks("clean", "vaadinBuildFrontend", "build")


vaadin {
    optimizeBundle = false
}

dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
