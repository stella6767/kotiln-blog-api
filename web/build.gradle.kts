
extra["snippetsDir"] = file("build/generated-snippets")


dependencies {

    api(project(":common"))
    implementation("net.jodah:expiringmap:0.5.10")
    implementation("it.ozimov:embedded-redis:0.7.3") { exclude(group = "org.slf4j", module = "slf4j-simple") }


    implementation("com.auth0:java-jwt:3.19.2")
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    // https://mvnrepository.com/artifact/org.ehcache/ehcache
    implementation("org.ehcache:ehcache:3.10.0")
// https://mvnrepository.com/artifact/javax.cache/cache-api
    implementation("javax.cache:cache-api:1.1.1")




    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-websocket")

    //testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
    //outputs.dir(snippetsDir)
}

//tasks.asciidoctor {
//    //inputs.dir(snippetsDir)
//
//    dependsOn(test)
//}
