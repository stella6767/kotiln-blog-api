
plugins {
    id("com.vaadin") version "23.2.6"
}


extra["vaadinVersion"] = "23.2.6"

dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
    }
}

repositories {
    maven { setUrl("https://maven.vaadin.com/vaadin-addons") }
}

defaultTasks("clean", "vaadinBuildFrontend", "build")

vaadin {
    //pnpmEnable = true
    optimizeBundle = false
    productionMode = true
    //generateBundle = true
}



dependencies {

    implementation(project(":module-core"))

    compileOnly("org.springframework.boot:spring-boot-devtools")
    // https://mvnrepository.com/artifact/org.dhatim/fastexcel
    implementation("org.dhatim:fastexcel:0.14.0")
    implementation("org.dhatim:fastexcel-reader:0.14.0")
    implementation("com.vaadin:vaadin-spring-boot-starter")

}