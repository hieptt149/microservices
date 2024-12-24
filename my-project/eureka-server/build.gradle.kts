plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	api(libs.spring.boot.starter.actuator)

	// bom implementation (~import mavenBom)
	implementation(platform(libs.spring.cloud.dependencies))
	api(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.eureka.server)

	// Test
	testImplementation(libs.spring.boot.starter.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
