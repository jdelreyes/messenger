plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ca.jdelreyes"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // spring-boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.2.2")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.2")
    // eureka
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.0")
    // security
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
