package cn.tinyhai.android.plugin

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.RepositoriesMode

@Suppress("UnstableApiUsage")
class TinyhaiInitPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {
        settings.dependencyResolutionManagement.apply {
            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
            repositories.apply {
                google()
                mavenCentral()
                jcenter()
                maven { it.setUrl("https://jitpack.io") }
                mavenLocal()
            }
        }
    }
}