package cn.tinyhai.android.config

import org.gradle.api.JavaVersion

object DefaultCommonConfig : AndroidConfig {
    override val compileSdk: Int = 31
    override val buildToolsVersion: String = "31.0.0"
    override val defaultConfig: DefaultConfig = object : DefaultConfig {
        override val minSdk: Int = 21
        override val targetSdk: Int = 31
        override val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
    override val compileOptions: CompileOptions = object : CompileOptions {
        override val sourceCompatibility: JavaVersion = JavaVersion.VERSION_1_8
        override val targetCompatibility: JavaVersion = JavaVersion.VERSION_1_8
    }
    override val composeOptions: ComposeOptions = object : ComposeOptions {
        override val kotlinCompilerExtensionVersion: String = "1.1.1"
    }
}