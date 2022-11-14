dependencies {

    implementation(project(":module-core"))

    // https://mvnrepository.com/artifact/com.github.okihouse/autocomplete
    implementation("com.github.okihouse:autocomplete:1.0.2")
    implementation("com.auth0:java-jwt:3.19.2")
    implementation("net.jodah:expiringmap:0.5.10")
    implementation("io.findify:s3mock_2.13:0.2.6")


    // https://mvnrepository.com/artifact/io.github.serpro69/kotlin-faker
    implementation("io.github.serpro69:kotlin-faker:1.11.0")
    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("it.ozimov:embedded-redis:0.7.3") { exclude(group = "org.slf4j", module = "slf4j-simple") }
    //implementation("org.springframework.boot:spring-boot-starter-websocket")


    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
// https://mvnrepository.com/artifact/nz.net.ultraq.thymeleaf/thymeleaf-layout-dialect
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")
    // https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity5
    //implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.1.0.RC2")


    //webJar
    // https://mvnrepository.com/artifact/org.webjars/webjars-locator-core
    implementation("org.webjars:webjars-locator-core:0.52")
    implementation("org.webjars.npm:alpinejs:3.10.5")
    implementation("org.webjars.npm:htmx.org:1.8.4")
    implementation("org.webjars.npm:bootstrap:5.2.2")
// https://mvnrepository.com/artifact/org.webjars.npm/antd
    //implementation("org.webjars.npm:antd:4.20.1")



    testImplementation("org.springframework.security:spring-security-test")
    //testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.testcontainers:mariadb:1.17.5")
    testImplementation("org.testcontainers:testcontainers:1.17.5")
    testImplementation("org.testcontainers:junit-jupiter:1.17.5")


}