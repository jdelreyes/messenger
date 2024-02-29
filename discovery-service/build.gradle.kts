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

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.2")
//    eureka
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:4.1.0")
//    security
    implementation("org.springframework.boot:spring-boot-starter-security:3.2.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
