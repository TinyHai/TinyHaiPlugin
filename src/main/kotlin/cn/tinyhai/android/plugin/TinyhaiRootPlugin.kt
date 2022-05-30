package cn.tinyhai.android.plugin

import cn.tinyhai.android.PLUGIN_ROOT_NAME
import cn.tinyhai.android.dsl.TinyhaiRootScoped
import org.gradle.api.Plugin
import org.gradle.api.Project

class TinyhaiRootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        if (target.rootProject !== target) {
            throw IllegalArgumentException("$PLUGIN_ROOT_NAME 不能应用到非根项目")
        }
        val tinyhaiRootScoped = TinyhaiRootScoped.setup(target)

        // 为子项目添加依赖仓库地址
        target.subprojects {
            it.repositories.apply {
                google()
                mavenCentral()
                maven { mavenRepository -> mavenRepository.url = it.uri("https://jitpack.io") }
                mavenLocal()
                tinyhaiRootScoped.repositories.invoke(this)
            }
        }
    }
}