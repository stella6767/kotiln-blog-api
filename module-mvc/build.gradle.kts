


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
    testImplementation("org.springframework.security:spring-security-test")
    //testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.testcontainers:mariadb:1.17.5")


}