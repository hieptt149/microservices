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
    alias(libs.plugins.jib)
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation(libs.spring.boot.actuator)
    implementation(libs.spring.boot.data.jpa)
    implementation(libs.spring.boot.validation)
    implementation(libs.spring.boot.web)

    // bom implementation (~import mavenBom)
    implementation(platform(libs.spring.cloud.dependencies))
    implementation(libs.spring.cloud.config)
    implementation(libs.spring.cloud.eureka.client)

    implementation(libs.lombok)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    implementation(libs.openapi)
    implementation(libs.micrometer.prometheus)
    runtimeOnly(libs.spring.boot.devtools)
    runtimeOnly(libs.h2database)
    runtimeOnly(libs.opentelemetry)

    // Test
    testImplementation(libs.spring.boot.test)

    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

group = "com.hieptt149"
version = "0.0.1-SNAPSHOT"
description = "loans"
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

jib {
    to {
        image = "hieptt149/loans:s13"
    }
}
