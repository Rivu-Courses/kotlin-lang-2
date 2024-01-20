plugins {
    kotlin("jvm") version "1.9.20"
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    application
}

group = "dev.rivu.courses"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.arrow-kt:arrow-core:1.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
    //implementation("io.insert-koin:koin-core:3.5.3")

    ksp("me.tatarka.inject:kotlin-inject-compiler-ksp:0.6.3")
    implementation("me.tatarka.inject:kotlin-inject-runtime:0.6.3")

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