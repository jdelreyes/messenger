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
//	spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.2.2")
//	lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
//	test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	db
	implementation("org.postgresql:postgresql:42.7.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.2")
//	spring security
	implementation("org.springframework.security:spring-security-crypto:6.2.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
