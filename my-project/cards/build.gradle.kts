/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.spring.boot.actuator)
    api(libs.spring.boot.data.jpa)
    api(libs.spring.boot.validation)
    api(libs.spring.boot.web)

    // bom implementation (~import mavenBom)
    implementation(platform(libs.spring.cloud.dependencies))
    api(libs.spring.cloud.config)
    api(libs.spring.cloud.eureka.client)

    api(libs.lombok)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    api(libs.openapi)
    runtimeOnly(libs.spring.boot.devtools)
    runtimeOnly(libs.h2database)

    // Test
    testImplementation(libs.spring.boot.test)

    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
description = "cards"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
