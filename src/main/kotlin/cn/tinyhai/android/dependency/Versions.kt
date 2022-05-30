package cn.tinyhai.android.dependency

internal object DefaultVersions {
    class Modules(
        moduleVersion: String
    ) {
        val base = moduleVersion
        val common = moduleVersion
        val request = moduleVersion
        val version = moduleVersion
        val login = moduleVersion
        val flutterBridge = moduleVersion
    }

    const val appCompat = "1.4.1"
    const val activity = "1.4.0"
    const val fragment = "1.4.1"
    const val material = "1.1.0"
    const val lifecycle = "2.4.1"
    const val compose = "1.1.1"
    const val androidXHilt = "1.0.0"
    const val core = "1.7.0"
    const val constraintLayout = "2.1.3"
    const val coordinatorLayout = "1.2.0"
    const val recyclerView = "1.2.1"
    const val junit4 = "4.13.2"
    const val testEspresso = "3.4.0"
    const val gson = "2.8.6"
    const val glide = "4.9.0"
    const val hilt = "2.39.1"
    const val testCore = "1.4.0"
    const val testExtJunit = "1.1.3"
    const val testExtTruth = "1.4.0"
    const val coroutine = "1.6.1"
}