/**
 * Build configuration for Pantheon Application Module
 * ==================================================
 *
 * This module contains the main Micronaut backend service with Netty server,
 * HTTP client support, and native image compilation capabilities.
 *
 * Key features:
 * - Micronaut Application Framework 5.0.2
 * - JDK 25 toolchain
 * - GraalVM Native Image support
 * - HTTP validation and serialization
 * - Docker container support
 */

plugins {
    // Kotlin language support
    id("org.jetbrains.kotlin.plugin.allopen") version "2.3.21"
    alias(libs.plugins.kotlinJvm)

    // Annotation processing
    id("com.google.devtools.ksp") version "2.3.7"

    // Micronaut Framework
    id("io.micronaut.application") version "5.0.2"
    id("io.micronaut.aot") version "5.0.2"

    // Packaging
    id("com.gradleup.shadow") version "9.4.1"
}

// -----------------------------------------------------------------------------
// Project Metadata
// -----------------------------------------------------------------------------
version = "0.1"
group = "kaishiro.pantheon"


// -----------------------------------------------------------------------------
// Kotlin Version Configuration
// -----------------------------------------------------------------------------
// Retrieve Kotlin version from project properties
// This ensures consistency with the root project configuration
val kotlinVersion = project.properties.get("kotlinVersion")


// -----------------------------------------------------------------------------
// Repository Configuration
// -----------------------------------------------------------------------------
// Centralized repository configuration via settings.gradle.kts
// Additional repositories can be added here if needed for this module
repositories {
    mavenCentral()
}


// -----------------------------------------------------------------------------
// Dependency Configuration
// -----------------------------------------------------------------------------
dependencies {
    // -------------------------------------------------------------------------
    // Kotlin Symbol Processing (KSP) - Compile-time annotation processing
    // -------------------------------------------------------------------------
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")

    // -------------------------------------------------------------------------
    // Implementation Dependencies - Required for compilation and runtime
    // -------------------------------------------------------------------------
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")

    // -------------------------------------------------------------------------
    // Compile-only Dependencies - Required for compilation but not runtime
    // -------------------------------------------------------------------------
    compileOnly("io.micronaut:micronaut-http-client")

    // -------------------------------------------------------------------------
    // Runtime-only Dependencies - Required only at runtime
    // -------------------------------------------------------------------------
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("tools.jackson.module:jackson-module-kotlin")

    // -------------------------------------------------------------------------
    // Test Dependencies
    // -------------------------------------------------------------------------
    testImplementation("io.micronaut:micronaut-http-client")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


// -----------------------------------------------------------------------------
// Application Configuration
// -----------------------------------------------------------------------------
// Configures the main application entry point for executable JAR creation
application {
    mainClass = "kaishiro.pantheon.app.MainKt"
}


// -----------------------------------------------------------------------------
// Kotlin Configuration
// -----------------------------------------------------------------------------
// Configures Kotlin compiler and toolchain settings
kotlin {
    // Use JDK 25 toolchain for compilation
    jvmToolchain(25)
}


// -----------------------------------------------------------------------------
// Java Configuration
// -----------------------------------------------------------------------------
// Configures Java compiler settings
java {
    toolchain {
        // Use JDK 25 toolchain for Java compilation
        languageVersion = JavaLanguageVersion.of(25)
    }
}


// -----------------------------------------------------------------------------
// GraalVM Native Image Configuration
// -----------------------------------------------------------------------------
// Configures native image compilation settings
// toolchainDetection = false: Use explicitly configured toolchain
graalvmNative.toolchainDetection = false

graalvmNative {
    binaries {
        all {
            // Enable shared arena support for improved performance
            // This allows the native image to use shared memory for allocations
            buildArgs.add("-H:+SharedArenaSupport")
        }
    }
}


// -----------------------------------------------------------------------------
// Micronaut Configuration
// -----------------------------------------------------------------------------
// Configures Micronaut Framework settings and AOT optimizations
micronaut {
    // Use Micronaut version matching the plugin version
    version.set("5.0.2")

    // Configure runtime server (Netty)
    runtime("netty")

    // Configure test runtime (JUnit 5)
    testRuntime("junit5")

    // Configure annotation processing
    processing {
        // Enable incremental processing for faster builds
        incremental(true)

        // Specify packages to scan for annotations
        annotations("kaishiro.pantheon.*")
    }

    // -----------------------------------------------------------------------
    // AOT (Ahead-of-Time) Compilation Configuration
    // -----------------------------------------------------------------------
    // These optimizations reduce startup time and memory footprint
    aot {
        // Disable service loading optimization (can cause issues in some cases)
        optimizeServiceLoading = false

        // Disable YAML to Java conversion (use properties instead)
        convertYamlToJava = false

        // Enable precomputation of operations for faster startup
        precomputeOperations = true

        // Enable environment caching for improved performance
        cacheEnvironment = true

        // Enable class loading optimization
        optimizeClassLoading = true

        // Enable environment deduction for optimized configuration
        deduceEnvironment = true

        // Enable Netty server optimization
        optimizeNetty = true

        // Enable optimized Logback XML configuration
        replaceLogbackXml = true
    }
}


// -----------------------------------------------------------------------------
// Docker Configuration
// -----------------------------------------------------------------------------
// Configures Dockerfile generation for containerized deployment
tasks.named<io.micronaut.gradle.docker.MicronautDockerfile>("dockerfile") {
    // Use GraalVM JDK community image for JDK 25
    baseImage = "ghcr.io/graalvm/jdk-community:25"
}


// -----------------------------------------------------------------------------
// Test Configuration
// -----------------------------------------------------------------------------
// Configures test task behavior
//
// Note: This configuration prevents test tasks from failing when no tests
// are discovered. This is useful during development when tests may not be
// fully implemented yet.
//
// Reference: https://docs.gradle.org/current/userguide/upgrading_major_version_9.html#test_task_fails_when_no_tests_are_discovered
tasks.withType<AbstractTestTask>().configureEach {
    failOnNoDiscoveredTests = false
}
