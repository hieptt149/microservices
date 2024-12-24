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

dependencies {
	implementation(libs.spring.boot.starter.actuator)

	// bom implementation (~import mavenBom)
	implementation(platform(libs.spring.cloud.dependencies))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.eureka.server)

	// Test
	testImplementation(libs.spring.boot.starter.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
	to {
		image = "hieptt149/eureka-server:s8"
	}
}
