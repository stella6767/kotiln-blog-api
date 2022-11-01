import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//buildscript {
//    dependencies {
//        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.0.RELEASE")
//    }
//
//}

plugins {
    id("org.springframework.boot") version "2.7.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
}

java.sourceCompatibility = JavaVersion.VERSION_11

val projectGroup: String by project
val applicationVersion: String by project


allprojects {

    group = projectGroup
    version = applicationVersion

    extra["snippetsDir"] = file("build/generated-snippets")
    extra["ioCloudVersion"] = "2.4.2"


    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")




    dependencyManagement {
        imports {
            mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:${property("ioCloudVersion")}")
        }
    }

    repositories {
        mavenCentral()
        //google()
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

    tasks.test {
        //outputs.dir(snippetsDir)
    }


//tasks.asciidoctor {
//    //inputs.dir(snippetsDir)
//
//    dependsOn(test)
//}

}



subprojects {
    
    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }


    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("io.awspring.cloud:spring-cloud-starter-aws")
        implementation("io.github.microutils:kotlin-logging:2.1.23")
        implementation("org.springframework.boot:spring-boot-starter-aop:2.7.0")
        //implementation("org.springframework.boot:spring-boot-starter-actuator")
        runtimeOnly("com.h2database:h2")
        runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

        implementation("org.springframework.boot:spring-boot-starter-cache")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }


}


