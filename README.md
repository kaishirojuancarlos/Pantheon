# Pantheon

A modern Kotlin-based application framework built on Micronaut, featuring a Compose Multiplatform UI library and integrated utility modules.

## Overview

Pantheon is a comprehensive Kotlin application project that combines the power of Micronaut for backend services with Compose Multiplatform for cross-platform user interfaces. The project is structured as a multi-module Gradle build with a core library module and an application module, along with the integrated Hive utilities library.

The project leverages modern Java and Kotlin technologies including:
- **Micronaut Framework 5.x** for reactive, cloud-native applications
- **Kotlin 2.x** with full JVM 25 support
- **Compose Multiplatform** for declarative UI development
- **GraalVM Native Image** for native compilation and reduced startup times
- **Kotlin Symbol Processing (KSP)** for compile-time code generation

## Project Structure

```
Pantheon/
├── app/
│   └── src/main/kotlin/kaishiro/pantheon/app/
│       └── main.kt                    # Application entry point
├── library/
│   └── src/main/kotlin/kaishiro/pantheon/app/core/
│       └── main.kt                    # Core library entry point
├── Hive/                            # Included as a composite build
│   └── library/
│       └── src/main/kotlin/kaishiro/hive/
│           ├── constant/
│           │   ├── io/
│           │   │   └── system/
│           │   │       └── system-io-constants.kt
│           │   └── text/
│           │       └── text-constants.kt
│           └── default/
│               └── io/
│                   └── io-defaults.kt
├── build.gradle.kts                 # Root project build configuration
├── settings.gradle.kts              # Project structure and plugin management
├── gradle/
│   ├── libs.versions.toml            # Centralized dependency version management
│   └── gradle.properties             # Gradle daemon JVM configuration
├── gradle.properties                 # Project-wide configuration properties
└── micronaut-cli.yml                 # Micronaut CLI configuration
```

### Modules

- **`app`**: The main application module containing the Micronaut-based backend service with Netty server
- **`library`**: The core Compose Multiplatform UI library with desktop application support
- **`Hive`**: An included composite build providing utility classes and constants

### Key Dependencies

| Category | Technology | Version |
|----------|------------|---------|
| Framework | Micronaut | 5.0.2 |
| Language | Kotlin | 2.3.21 |
| JVM | Java | 25 |
| UI | Compose Multiplatform | 1.11.1 |
| Coroutines | Kotlinx Coroutines | 1.11.0 |
| Serialization | Jackson | - |
| Validation | Micronaut HTTP Validation | - |
| Logging | Logback | - |

## Build Configuration

The project uses **Gradle Kotlin DSL** with **Version Catalogs** for centralized dependency management. Key configuration highlights:

- **Kotlin JVM Toolchain**: Configured for JDK 25
- **GraalVM Support**: Native image compilation enabled
- **Plugin Management**: Centralized in `settings.gradle.kts`
- **Dependency Management**: Centralized version catalog in `gradle/libs.versions.toml`

### Build Tasks

| Task | Description |
|------|-------------|
| `build` | Compiles and packages all modules |
| `run` | Executes the main application |
| `test` | Runs all unit tests |
| `dockerfile` | Generates Dockerfile for containerized deployment |
| `graalvmNative` | Creates native image using GraalVM |

## Features

### Backend (App Module)
- **Micronaut Application**: Fully configured with AOT optimizations
- **HTTP Client & Server**: Netty-based networking with validation support
- **Configuration**: Properties-based configuration with validation
- **Serialization**: Jackson-based JSON serialization
- **Native Image**: GraalVM native compilation with shared arena support
- **Docker Support**: Automated Dockerfile generation

### Frontend (Library Module)
- **Compose Multiplatform**: Cross-platform UI components
- **Desktop Support**: Native packaging for Windows, macOS, and Linux
- **Material 3 Design**: Modern UI components using Material Design 3
- **Lifecycle Integration**: AndroidX Lifecycle components for state management
- **Constraint Layout**: Advanced layout management

### Hive Utilities
- **I/O Constants**: Predefined constants for standard system streams
- **Text Constants**: Common character and string constants
- **Default Values**: Sensible default values for I/O operations

## Getting Started

### Prerequisites

- **JDK 25** (required for compilation)
- **Gradle 9.6.1** (included via wrapper)
- **GraalVM** (optional, for native image compilation)

### Building

```bash
# Build all modules
./gradlew build

# Run the application
./gradlew run

# Run tests
./gradlew test

# Generate native image
./gradlew graalvmNative

# Generate Dockerfile
./gradlew dockerfile
```

### Running with Docker

```bash
# Build the Docker image
docker build -t pantheon .

# Run the container
docker run -p 8080:8080 pantheon
```

## Configuration

### Micronaut CLI

The project includes Micronaut CLI configuration for rapid application development:
- Application type: Default
- Package: `kaishiro.pantheon`
- Features: HTTP client, validation, serialization, native image support

### AOT Optimizations

Micronaut AOT is configured with the following optimizations:
- **Precompute Operations**: Enabled for faster startup
- **Cache Environment**: Enabled for environment-based optimizations
- **Optimize Class Loading**: Enabled for reduced class loading overhead
- **Deduce Environment**: Enabled for environment-specific optimizations
- **Optimize Netty**: Enabled for Netty server optimizations
- **Replace Logback XML**: Enabled for optimized logging configuration

## License

This project is licensed under the Mozilla Public License Version 2.0. See the [LICENSE](LICENSE) file for details.
