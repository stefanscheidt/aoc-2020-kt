plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

repositories {
    mavenCentral()
}

tasks {
    compileKotlin {
        dependsOn("ktlintCheck")
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    val junitVersion = "5.8.1"
    val kotestVersion = "4.6.3"

    implementation("com.github.h0tk3y.betterParse:better-parse:0.4.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}
