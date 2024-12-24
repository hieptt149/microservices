plugins {
	`java-library`
	`maven-publish`
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	api(libs.org.springframework.boot.spring.boot.starter.actuator)
	api(libs.org.springframework.cloud.spring.cloud.starter.config)
	implementation(libs.spring.cloud.eureka.server)

	// Test
	testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
