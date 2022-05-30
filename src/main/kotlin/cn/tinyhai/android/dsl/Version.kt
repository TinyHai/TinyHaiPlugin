package cn.tinyhai.android.dsl

import cn.tinyhai.android.dependency.DefaultVersions

interface VersionScoped {
    val appCompat: String
    val activity: String
    val fragment: String
    val material: String
    val lifecycle: String
    val compose: String
    val androidXHilt: String
    val core: String
    val constraintLayout: String
    val coordinatorLayout: String
    val recyclerView: String
    val junit4: String
    val testEspresso: String
    val gson: String
    val glide: String
    val hilt: String
    val testCore: String
    val testExtJunit: String
    val testExtTruth: String
    val coroutine: String

    companion object {
        operator fun invoke(): VersionScoped {
            return VersionScopedImpl()
        }
    }
}

interface MutableVersionScoped : VersionScoped {
    override var appCompat: String
    override var activity: String
    override var fragment: String
    override var material: String
    override var lifecycle: String
    override var compose: String
    override var androidXHilt: String
    override var core: String
    override var constraintLayout: String
    override var coordinatorLayout: String
    override var recyclerView: String
    override var junit4: String
    override var testEspresso: String
    override var gson: String
    override var glide: String
    override var hilt: String
    override var testCore: String
    override var testExtJunit: String
    override var testExtTruth: String
    override var coroutine: String
}

internal class VersionScopedImpl : MutableVersionScoped {
    override var appCompat = DefaultVersions.appCompat
    override var activity = DefaultVersions.activity
    override var fragment = DefaultVersions.fragment
    override var material = DefaultVersions.material
    override var lifecycle = DefaultVersions.lifecycle
    override var compose = DefaultVersions.compose
    override var androidXHilt = DefaultVersions.androidXHilt
    override var core = DefaultVersions.core
    override var constraintLayout = DefaultVersions.constraintLayout
    override var coordinatorLayout = DefaultVersions.coordinatorLayout
    override var recyclerView = DefaultVersions.recyclerView
    override var junit4 = DefaultVersions.junit4
    override var testEspresso = DefaultVersions.testEspresso
    override var gson = DefaultVersions.gson
    override var glide = DefaultVersions.glide
    override var hilt = DefaultVersions.hilt
    override var testCore = DefaultVersions.testCore
    override var testExtJunit: String = DefaultVersions.testExtJunit
    override var testExtTruth: String = DefaultVersions.testExtTruth
    override var coroutine: String = DefaultVersions.coroutine
}