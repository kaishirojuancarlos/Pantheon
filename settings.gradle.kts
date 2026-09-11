/**
 * Gradle settings configuration for Pantheon project.
 *
 * This file centralizes the configuration for:
 * - Plugin management: Repositories for Gradle plugins
 * - Dependency resolution: Repositories for project dependencies
 * - Project structure: Included subprojects and composite builds
 *
 * The configuration ensures consistent repository access across all modules.
 */

rootProject.name = "Pantheon"

/**
 * Plugin Management Configuration
 *
 * Defines repositories where Gradle should look for plugins.
 * This configuration is inherited by all subprojects.
 *
 * Repository Priority:
 * 1. Google Maven - For Android, Google, and AndroidX artifacts
 * 2. Maven Central - For most open-source dependencies
 * 3. Gradle Plugin Portal - For Gradle plugins
 */
pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

/**
 * Dependency Resolution Management Configuration
 *
 * Centralizes repository configuration for all project dependencies.
 * Uses PREFER_PROJECT mode to prioritize project-local repositories.
 *
 * Repository Priority:
 * 1. Google Maven - For Android, Google, and AndroidX artifacts
 * 2. Maven Central - For most open-source dependencies
 */
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

/**
 * Global Plugin Configuration
 *
 * Applies the Foojay resolver plugin globally for JDK toolchain management.
 * This plugin provides automatic resolution of JDK versions from Foojay.io.
 */
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

/**
 * Project Structure Configuration
 *
 * Includes the main project modules and composite builds:
 * - library: Core Compose Multiplatform library module
 * - app: Main Micronaut application module
 * - Hive: Composite build providing utility classes and constants
 */
include(":library", ":app")
includeBuild("Hive")
