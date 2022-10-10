import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//최상위 gradle.kts에서 plugin을 정의하고 하위모듈에서 불러온다.

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}


java.sourceCompatibility = JavaVersion.VERSION_11


allprojects {

    group = "com.example"
    version = "0.0.1-SNAPSHOT"


    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

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


}

subprojects {



    dependencies {
        implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter:2.0.4.RELEASE")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        // https://mvnrepository.com/artifact/com.github.gavlyukovskiy/p6spy-spring-boot-starter
        implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")
        implementation("io.github.microutils:kotlin-logging:2.1.23")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.13.3")
        // https://mvnrepository.com/artifact/io.github.serpro69/kotlin-faker
        implementation("io.github.serpro69:kotlin-faker:1.11.0")
        implementation("org.springframework.boot:spring-boot-starter-aop:2.7.0")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

    }


}


//project(":web") {
//    dependencies {
//        implementation(project(":common"))
//    }
//}