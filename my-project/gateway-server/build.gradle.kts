plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.jib)
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
	implementation(libs.spring.boot.starter.actuator)
	// bom implementation (~import mavenBom)
	implementation(platform(libs.spring.cloud.dependencies))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.gateway)
	implementation(libs.spring.cloud.eureka.client)
	developmentOnly(libs.spring.boot.devtools)

	// Test
	testImplementation(libs.reactor.test)
	testImplementation(libs.spring.boot.starter.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
	to {
		image = "hieptt149/gateway-server:s9"
	}
}
