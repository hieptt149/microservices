plugins {
	`java-library`
	`maven-publish`
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.jib)
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
description = "message"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.cloud.stream)
	implementation(libs.spring.cloud.stream.binder.rabbit)
	// bom implementation (~import mavenBom)
	implementation(platform(libs.spring.cloud.dependencies))

	// Test
	testImplementation(libs.spring.boot.test)
	testImplementation(libs.spring.cloud.stream.test.binder)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jib {
	to {
		image = "hieptt149/message:s13"
	}
}
