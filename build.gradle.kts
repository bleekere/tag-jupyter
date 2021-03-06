import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactId: String = "tag-jupyter"

group = "nl.knaw.huygens.tag"
version = "0.1.5"

plugins {
    kotlin("jvm") version "1.4.0"
    `maven-publish`
}

repositories {
    mavenLocal()
    maven("http://maven.huygens.knaw.nl/repository/")
    mavenCentral()
}

dependencies {
    implementation("nl.knaw.huygens.tag:tagml:0.560.5")
    implementation("nl.knaw.huygens.tag:tag-mct:0.1.2")
    implementation("nl.knaw.huygens:graphviz-wrapper:1.1")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/repo")
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "$group"
            artifactId = "$artifactId"
            version = "$version"
            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}