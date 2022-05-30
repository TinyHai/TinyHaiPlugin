package cn.tinyhai.android.extentions

import cn.tinyhai.android.dsl.TinyhaiRootScoped
import cn.tinyhai.android.dsl.TinyhaiScoped
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

val Project.Deps get() = TinyhaiScoped.obtain(this).deps

fun DependencyHandler.implementations(vararg dependencies: String) {
    dependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.apis(vararg apis: String) {
    apis.forEach {
        add("api", it)
    }
}

fun Project.tinyhai(block: TinyhaiScoped.() -> Unit) {
    TinyhaiScoped.obtain(this).block()
}

val Project.tinyhai: TinyhaiScoped get() = TinyhaiScoped.obtain(this)

fun Project.tinyhaiRoot(block: TinyhaiRootScoped.() -> Unit) {
    TinyhaiRootScoped.obtain(this).block()
}

val Project.tinyhaiRoot: TinyhaiRootScoped get() = TinyhaiRootScoped.obtain(this)