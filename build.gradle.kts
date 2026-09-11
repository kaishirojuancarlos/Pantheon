import org.gradle.buildconfiguration.tasks.UpdateDaemonJvm
import org.gradle.jvm.toolchain.JvmVendorSpec

/**
 * Root project build configuration for Pantheon.
 *
 * This file configures the Gradle daemon JVM settings and declares all plugins
 * used across the project modules. Plugins are applied in individual module
 * build files.
 *
 * Key features:
 * - GraalVM-based Gradle daemon for optimal Native Image support
 * - JDK 25 toolchain for all compilation tasks
 * - Centralized plugin version management via libs.versions.toml
 */
plugins {
    // Micronaut Framework plugins
    id("io.micronaut.application") version "5.0.2" apply false
    id("io.micronaut.aot") version "5.0.2" apply false

    // Kotlin plugins
    id("org.jetbrains.kotlin.plugin.allopen") version "2.3.21" apply false
    alias(libs.plugins.kotlinJvm) apply false

    // Processing plugins
    id("com.google.devtools.ksp") version "2.3.7" apply false

    // Packaging plugins
    id("com.gradleup.shadow") version "9.4.1" apply false

    // Compose Multiplatform plugins
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
}

/**
 * Configures the Gradle daemon to use GraalVM with JDK 25 support.
 * This ensures optimal performance for Native Image compilation tasks.
 *
 * Settings:
 * - Java Language Version: 25
 * - Vendor: GraalVM (for Native Image support)
 * - Native Image Capable: true (enables GraalVM Native Image features)
 */
tasks.named<UpdateDaemonJvm>("updateDaemonJvm") {
    languageVersion = JavaLanguageVersion.of(25)
    vendor = JvmVendorSpec.GRAAL_VM
    nativeImageCapable = true
}
