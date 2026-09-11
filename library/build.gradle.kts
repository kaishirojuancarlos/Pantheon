import org.jetbrains.compose.desktop.application.dsl.TargetFormat

/**
 * Build configuration for Pantheon Library Module
 * ==============================================
 *
 * This module contains the Compose Multiplatform UI library with desktop
 * application support, Material 3 design components, and integration with
 * Micronaut core functionality.
 *
 * Key features:
 * - Compose Multiplatform for cross-platform UI
 * - Desktop application packaging (DMG, MSI, DEB)
 * - Material Design 3 components
 * - Kotlin Coroutines integration
 * - Micronaut core integration
 */

plugins {
    // Compose Multiplatform plugins for cross-platform UI development
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinJvm)

    // Micronaut Library plugin for library projects
    id("io.micronaut.library")
}


// -----------------------------------------------------------------------------
// Dependency Configuration
// -----------------------------------------------------------------------------
dependencies {
    // -------------------------------------------------------------------------
    // Compose Multiplatform Core Libraries
    // -------------------------------------------------------------------------
    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)

    // -------------------------------------------------------------------------
    // Compose Material 3 Design System
    // -------------------------------------------------------------------------
    implementation(libs.compose.material3)

    // -------------------------------------------------------------------------
    // Compose UI Components
    // -------------------------------------------------------------------------
    implementation(libs.compose.ui)
    implementation(libs.compose.components.resources)
    implementation(libs.compose.uiToolingPreview)

    // -------------------------------------------------------------------------
    // AndroidX Lifecycle Components for Compose State Management
    // -------------------------------------------------------------------------
    implementation(libs.androidx.lifecycle.viewmodelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    // -------------------------------------------------------------------------
    // Platform-Specific Compose Dependencies
    // -------------------------------------------------------------------------
    // Current OS-specific desktop dependencies
    implementation(compose.desktop.currentOs)

    // -------------------------------------------------------------------------
    // Kotlin Coroutines for Swing Integration
    // -------------------------------------------------------------------------
    implementation(libs.kotlinx.coroutinesSwing)

    // -------------------------------------------------------------------------
    // Duplicate uiToolingPreview removed (already included above)
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Micronaut Core Dependencies for DI and Configuration
    // -------------------------------------------------------------------------
    implementation("io.micronaut:micronaut-core")
    implementation("io.micronaut:micronaut-inject")

    // -------------------------------------------------------------------------
    // External Dependencies
    // -------------------------------------------------------------------------
    // Constraint Layout for advanced UI layout management
    implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.8.2")

    // -------------------------------------------------------------------------
    // Hive Utilities Library
    // -------------------------------------------------------------------------
    // Local dependency on the Hive library for utility classes and constants
    implementation("kaishiro.hive:library:1.1.0")
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
// Compose Desktop Configuration
// -----------------------------------------------------------------------------
// Configures desktop application settings and packaging
compose.desktop {
    application {
        // Main class for the desktop application
        mainClass = "kaishiro.pantheon.app.core.MainKt"

        // -------------------------------------------------------------------
        // Native Distribution Configuration
        // -------------------------------------------------------------------
        // Configure native packaging formats for different platforms
        nativeDistributions {
            // Generate installers for all major desktop platforms
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            // Package name for the generated installers
            packageName = "pantheon.core"

            // Package version for the generated installers
            packageVersion = "1.0.0"
        }
    }
}


// -----------------------------------------------------------------------------
// Micronaut Configuration
// -----------------------------------------------------------------------------
// Configures Micronaut Framework settings for library module
micronaut {
    // Use Micronaut version matching the plugin version
    version.set("5.0.2")

    // Configure runtime server (Netty) - Required for full Micronaut support
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
}
