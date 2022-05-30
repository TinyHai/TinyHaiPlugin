package cn.tinyhai.android.config

import com.android.build.api.dsl.*
import cn.tinyhai.android.dsl.AndroidScoped
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KOTLIN_OPTIONS_DSL_NAME

interface AndroidConfig : Configuration {
    val compileSdk: Int
    val buildToolsVersion: String
    val defaultConfig: DefaultConfig
    val compileOptions: CompileOptions
    val composeOptions: ComposeOptions

    override fun configure(project: Project) = Unit
}

interface DefaultConfig {
    val minSdk: Int
    val targetSdk: Int
    val testInstrumentationRunner: String
}

interface CompileOptions {
    val sourceCompatibility: JavaVersion
    val targetCompatibility: JavaVersion
}

interface ComposeOptions {
    val kotlinCompilerExtensionVersion: String
}

internal abstract class AbstractAndroidConfig(protected val androidScoped: AndroidScoped) : AndroidConfig {
    override fun configure(project: Project) {
        super.configure(project)
        val kotlinCompile = project.extensions.findByName(KOTLIN_OPTIONS_DSL_NAME) as? KotlinJvmOptions
        if (androidScoped.useCompose) {
            kotlinCompile?.apply {
                jvmTarget = "1.8"
            }
        }
    }
}

@Suppress("UnstableApiUsage")
internal class AndroidConfigImpl(androidScoped: AndroidScoped, default: DefaultCommonConfig) :
    AbstractAndroidConfig(androidScoped) {

    override val compileSdk: Int = DefaultCommonConfig.compileSdk

    override val buildToolsVersion: String = DefaultCommonConfig.buildToolsVersion

    override val defaultConfig: DefaultConfig = DefaultCommonConfig.defaultConfig

    override val compileOptions: CompileOptions = DefaultCommonConfig.compileOptions

    override val composeOptions: ComposeOptions = DefaultCommonConfig.composeOptions

    override fun configure(project: Project) {
        val commonExtension = project.extensions.findByType(CommonExtension::class.java) ?: return
        if (!androidScoped.applyDefault) {
            return
        }
        commonApply(commonExtension)
        when (commonExtension) {
            is ApplicationExtension -> {
                commonExtension.apply()
            }
            is LibraryExtension -> {
                commonExtension.apply()
            }
        }
        super.configure(project)
    }

    private fun ApplicationExtension.apply() {
        defaultConfig {
            minSdk = this@AndroidConfigImpl.defaultConfig.minSdk
            targetSdk = this@AndroidConfigImpl.defaultConfig.targetSdk
        }
    }

    private fun LibraryExtension.apply() {
        defaultConfig {
            minSdk = this@AndroidConfigImpl.defaultConfig.minSdk
            targetSdk = this@AndroidConfigImpl.defaultConfig.targetSdk
        }
    }

    private fun commonApply(commonExtension: CommonExtension<*, *, *, *>) = commonExtension.let {
        it.compileSdk = compileSdk
        it.buildToolsVersion = buildToolsVersion
        it.compileOptions {
            sourceCompatibility = compileOptions.sourceCompatibility
            targetCompatibility = compileOptions.targetCompatibility
        }
        it.buildTypes {
            named("release") { release ->
                release.isMinifyEnabled = false
                release.proguardFiles(
                    commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            named("debug") { debug ->
                debug.isMinifyEnabled = false
                debug.proguardFiles(
                    commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        it.buildFeatures {
            viewBinding = androidScoped.viewBinding
            compose = androidScoped.useCompose
        }
        it.composeOptions {
            if (androidScoped.useCompose) {
                kotlinCompilerExtensionVersion = composeOptions.kotlinCompilerExtensionVersion
            }
        }
        it.dataBinding {
            isEnabled = androidScoped.dataBinding
        }
    }
}