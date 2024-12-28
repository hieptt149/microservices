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
	implementation(libs.spring.boot.starter)
	implementation(libs.spring.cloud.function.context)
	implementation(libs.spring.cloud.function.web)
	// bom implementation (~import mavenBom)
	implementation(platform(libs.spring.cloud.dependencies))

	// Test
	testImplementation(libs.spring.boot.test)
	testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
