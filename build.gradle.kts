import org.gradle.buildconfiguration.tasks.UpdateDaemonJvm
import org.gradle.jvm.toolchain.JvmVendorSpec

plugins {
    //id("org.jetbrains.kotlin.jvm") version "2.3.21" apply false
    id("org.jetbrains.kotlin.plugin.allopen") version "2.3.21" apply false
    id("com.google.devtools.ksp") version "2.3.7" apply false
    id("io.micronaut.application") version "5.0.2" apply false
    id("com.gradleup.shadow") version "9.4.1" apply false
    id("io.micronaut.aot") version "5.0.2" apply false

	
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    //alias(libs.plugins.kotlinMultiplatform) apply false
}

tasks.named<UpdateDaemonJvm>("updateDaemonJvm")
{
	languageVersion = JavaLanguageVersion.of(25)
	vendor = JvmVendorSpec.GRAAL_VM
	nativeImageCapable = true
}

