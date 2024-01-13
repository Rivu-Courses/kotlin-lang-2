plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "dev.rivu.courses"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.arrow-kt:arrow-core:1.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
    implementation("io.insert-koin:koin-core:3.5.3")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}