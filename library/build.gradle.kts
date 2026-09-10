import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinJvm)
	id("io.micronaut.library")// version "5.0.2"
	//id("org.jetbrains.kotlin.kapt") version "2.4.10"
}

dependencies {
	implementation(libs.compose.runtime)
	implementation(libs.compose.foundation)
	implementation(libs.compose.material3)
	implementation(libs.compose.ui)
	implementation(libs.compose.components.resources)
	implementation(libs.compose.uiToolingPreview)
	implementation(libs.androidx.lifecycle.viewmodelCompose)
	implementation(libs.androidx.lifecycle.runtimeCompose)




    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)

	implementation("io.micronaut:micronaut-core")
	implementation("io.micronaut:micronaut-inject")
	//kapt("io.micronaut:micronaut-inject-java")
	


	implementation("tech.annexflow.compose:constraintlayout-compose-multiplatform:0.8.2")

	implementation("kaishiro.hive:library:1.1.0")
}

kotlin {
	jvmToolchain(25)
}
java {/*
	sourceCompatibility = JavaVersion.toVersion("25")
	targertCompatibility = JavaVersion.toVersion("25")*/
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

compose.desktop {
    application {
        mainClass = "kaishiro.pantheon.app.core.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "pantheon.core"
            packageVersion = "1.0.0"
        }
    }
}

micronaut {
	version.set("5.0.2")
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("kaishiro.pantheon.*")
    }
}

