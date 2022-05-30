package cn.tinyhai.android.dependency

import cn.tinyhai.android.dsl.VersionScoped
import kotlin.reflect.KProperty

interface Coroutine {
    val core: String
    val android: String
}

interface KotlinX {
    val Coroutine: Coroutine
}

interface AppCompat {
    val appCompat: String
    val appCompatResource: String
}

interface Activity {
    val activity: String
    val activityKtx: String
    val activityCompose: String
}

interface Lifecycle {
    val viewModelKtx: String
    val viewModelCompose: String
    val livedataKtx: String
    val runtimeKtx: String
    val viewModelSavedState: String
    val kaptCompiler: String
    val commonJava8: String
    val service: String
    val process: String
    val reactiveStream: String
}

interface Material {
    val material: String
    val materialIconsCore: String
    val materialIconsExtended: String
}

interface Runtime {
    val runtime: String
    val runtimeLiveData: String
    val runtimeRxJava2: String
}

interface Compose {
    val compiler: String
    val ui: String
    val animation: String
    val foundation: String
    val uiToolingPreview: String
    val uiTestJunit4: String

    val Material: Material
    val Runtime: Runtime
}

interface Hilt {
    val lifecycleViewModel: String
    val kaptCompiler: String
}

interface Core {
    val coreKtx: String
}

interface ConstraintLayout {
    val constraintLayout: String
}

interface CoordinatorLayout {
    val coordinatorLayout: String
}

interface RecycleView {
    val recyclerView: String
}

interface Espresso {
    val espressoCore: String
}

interface TestExt {
    val junit: String
    val junitKtx: String
    val truth: String
}

interface TestCore {
    val core: String
    val coreKtx: String
}

interface Test {
    val Espresso: Espresso
    val Ext: TestExt
    val Core: TestCore
}

interface Fragment {
    val fragmentKtx: String
}

interface AndroidX {
    val AppCompat: AppCompat
    val Fragment: Fragment
    val Activity: Activity
    val Lifecycle: Lifecycle
    val Compose: Compose
    val Hilt: Hilt
    val Core: Core
    val ConstraintLayout: ConstraintLayout
    val CoordinatorLayout: CoordinatorLayout
    val RecycleView: RecycleView
    val Test: Test
}

interface Modules : Iterable<Pair<String, () -> String>> {
    val base: String
    val common: String
    val request: String
    val version: String
    val login: String
    val flutterBridge: String

    companion object {
        const val groupId = "cn.tinyhai.android.modules"
    }
}

interface DebugModules : Modules

interface ReleaseModules : Modules

interface ModulesWithoutVersion : Modules

interface DebugModulesWithoutVersion : ModulesWithoutVersion

interface ReleaseModulesWithoutVersion : ModulesWithoutVersion

class Deps(
    private val versionScoped: VersionScoped
) {
    val AndroidX: AndroidX = object : AndroidX {
        override val AppCompat: AppCompat = object : AppCompat {
            override val appCompat: String get() = "androidx.appcompat:appcompat:${versionScoped.appCompat}"
            override val appCompatResource: String get() = "androidx.appcompat:appcompat-resources:${versionScoped.appCompat}"
        }
        override val Fragment: Fragment = object : Fragment {
            override val fragmentKtx: String get() = "androidx.fragment:fragment-ktx:${versionScoped.fragment}"
        }
        override val Activity: Activity = object : Activity {
            override val activity: String get() = "androidx.activity:activity:${versionScoped.activity}"
            override val activityKtx: String get() = "androidx.activity:activity-ktx:${versionScoped.activity}"
            override val activityCompose: String get() = "androidx.activity:activity-compose:${versionScoped.activity}"
        }
        override val Lifecycle: Lifecycle = object : Lifecycle {
            override val viewModelKtx: String get() = "androidx.lifecycle:lifecycle-viewmodel-ktx:${versionScoped.lifecycle}"
            override val viewModelCompose: String get() = "androidx.lifecycle:lifecycle-viewmodel-compose:${versionScoped.lifecycle}"
            override val livedataKtx: String get() = "androidx.lifecycle:lifecycle-livedata-ktx:${versionScoped.lifecycle}"
            override val runtimeKtx: String get() = "androidx.lifecycle:lifecycle-runtime-ktx:${versionScoped.lifecycle}"
            override val viewModelSavedState: String get() =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:${versionScoped.lifecycle}"
            override val kaptCompiler: String get() = "androidx.lifecycle:lifecycle-compiler:${versionScoped.lifecycle}"
            override val commonJava8: String get() = "androidx.lifecycle:lifecycle-common-java8:${versionScoped.lifecycle}"
            override val service: String get() = "androidx.lifecycle:lifecycle-service:${versionScoped.lifecycle}"
            override val process: String get() = "androidx.lifecycle:lifecycle-process:${versionScoped.lifecycle}"
            override val reactiveStream: String get() = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${versionScoped.lifecycle}"
        }
        override val Compose: Compose = object : Compose {
            override val compiler: String get() = "androidx.compose.compiler:compiler:${versionScoped.compose}"
            override val ui: String get() = "androidx.compose.ui:ui:${versionScoped.compose}"
            override val animation: String get() = "androidx.compose.animation:animation:${versionScoped.compose}"
            override val foundation: String get() = "androidx.compose.foundation:foundation:${versionScoped.compose}"
            override val uiToolingPreview: String get() = "androidx.compose.ui:ui-tooling-preview:${versionScoped.compose}"
            override val uiTestJunit4: String get() = "androidx.compose.ui:ui-test-junit4:${versionScoped.compose}"

            override val Material = object : Material {
                override val material: String get() = "androidx.compose.material:material:${versionScoped.compose}"
                override val materialIconsCore: String get() =
                    "androidx.compose.material:material-icons-core:${versionScoped.compose}"
                override val materialIconsExtended: String get() =
                    "androidx.compose.material:material-icons-extended:${versionScoped.compose}"
            }

            override val Runtime = object : Runtime {
                override val runtime: String get() = "androidx.compose.runtime:runtime:${versionScoped.compose}"
                override val runtimeLiveData: String get() = "androidx.compose.runtime:runtime-livedata:${versionScoped.compose}"
                override val runtimeRxJava2: String get() = "androidx.compose.runtime:runtime-rxjava2:${versionScoped.compose}"
            }
        }
        override val Hilt: Hilt = object : Hilt {
            override val lifecycleViewModel: String get() = "androidx.hilt:hilt-lifecycle-viewmodel:${versionScoped.androidXHilt}"
            override val kaptCompiler: String get() = "androidx.hilt:hilt-compiler:${versionScoped.androidXHilt}"
        }
        override val Core: Core = object : Core {
            override val coreKtx: String get() = "androidx.core:core-ktx:${versionScoped.core}"
        }

        override val ConstraintLayout: ConstraintLayout = object : ConstraintLayout {
            override val constraintLayout: String get() =
                "androidx.constraintlayout:constraintlayout:${versionScoped.constraintLayout}"
        }

        override val CoordinatorLayout: CoordinatorLayout = object : CoordinatorLayout {
            override val coordinatorLayout: String get() =
                "androidx.coordinatorlayout:coordinatorlayout:${versionScoped.coordinatorLayout}"
        }

        override val RecycleView: RecycleView = object : RecycleView {
            override val recyclerView: String get() = "androidx.recyclerview:recyclerview:${versionScoped.recyclerView}"
        }

        override val Test: Test = object : Test {
            override val Espresso: Espresso = object : Espresso {
                override val espressoCore: String get() = "androidx.test.espresso:espresso-core:${versionScoped.testEspresso}"
            }
            override val Ext: TestExt = object : TestExt {
                override val junit: String get() = "androidx.test.ext:junit:${versionScoped.testExtJunit}"
                override val junitKtx: String get() = "androidx.test.ext:junit-ktx:${versionScoped.testExtJunit}"
                override val truth: String get() = "androidx.test.ext:truth:${versionScoped.testExtTruth}"
            }

            override val Core: TestCore = object : TestCore {
                override val core: String get() = "androidx.test:core:${versionScoped.testCore}"
                override val coreKtx: String get() = "androidx.test:core-ktx:${versionScoped.testCore}"
            }
        }
    }

    val KotlinX: KotlinX = object : KotlinX {
        override val Coroutine: Coroutine = object : Coroutine {
            override val core: String get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versionScoped.coroutine}"
            override val android: String get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versionScoped.coroutine}"
        }
    }

//    val ReleaseModules: Modules = object : ReleaseModules {
//        override val base: String by this@Deps
//        override val common: String by this@Deps
//        override val request: String by this@Deps
//        override val version: String by this@Deps
//        override val login: String by this@Deps
//        override val flutterBridge: String by this@Deps
//
//        private val properties: List<Pair<String, () -> String>> = listOf(
//            "base" to { base },
//            "common" to { common },
//            "request" to { request },
//            "version" to { version },
//            "login" to { login },
//            "flutterBridge" to { flutterBridge }
//        )
//
//        override fun iterator(): Iterator<Pair<String, () -> String>> {
//            return properties.iterator()
//        }
//    }
//
//    val DebugModules: Modules = object : DebugModules {
//        override val base: String by this@Deps
//        override val common: String by this@Deps
//        override val request: String by this@Deps
//        override val version: String by this@Deps
//        override val login: String by this@Deps
//        override val flutterBridge: String by this@Deps
//
//        private val properties: List<Pair<String, () -> String>> = listOf(
//            "base" to { base },
//            "common" to { common },
//            "request" to { request },
//            "version" to { version },
//            "login" to { login },
//            "flutterBridge" to { flutterBridge }
//        )
//
//        override fun iterator(): Iterator<Pair<String, () -> String>> {
//            return properties.iterator()
//        }
//    }
//
//    val DebugModulesWithoutVersion: ModulesWithoutVersion = object : DebugModulesWithoutVersion {
//        override val base: String by this@Deps
//        override val common: String by this@Deps
//        override val request: String by this@Deps
//        override val version: String by this@Deps
//        override val login: String by this@Deps
//        override val flutterBridge: String by this@Deps
//
//        private val properties: List<Pair<String, () -> String>> = listOf(
//            "base" to { base },
//            "common" to { common },
//            "request" to { request },
//            "version" to { version },
//            "login" to { login },
//            "flutterBridge" to { flutterBridge }
//        )
//
//        override fun iterator(): Iterator<Pair<String, () -> String>> {
//            return properties.iterator()
//        }
//    }
//
//    val ReleaseModulesWithoutVersion: ModulesWithoutVersion = object : ReleaseModulesWithoutVersion {
//        override val base: String by this@Deps
//        override val common: String by this@Deps
//        override val request: String by this@Deps
//        override val version: String by this@Deps
//        override val login: String by this@Deps
//        override val flutterBridge: String by this@Deps
//
//        private val properties: List<Pair<String, () -> String>> = listOf(
//            "base" to { base },
//            "common" to { common },
//            "request" to { request },
//            "version" to { version },
//            "login" to { login },
//            "flutterBridge" to { flutterBridge }
//        )
//
//        override fun iterator(): Iterator<Pair<String, () -> String>> {
//            return properties.iterator()
//        }
//    }
//
//    private operator fun getValue(thisRef: Modules, property: KProperty<*>): String {
//        val scoped = versionScoped.modulesVersionScoped
//        val version = when (property.name) {
//            "base" -> scoped.base
//            "common" -> scoped.common
//            "request" -> scoped.request
//            "login" -> scoped.login
//            "version" -> scoped.version
//            "flutterBridge" -> scoped.flutterBridge
//            else -> throw IllegalArgumentException("property.name = ${property.name}")
//        }
//        return when (thisRef) {
//            is DebugModules -> "${Modules.groupId}:${property.name.camelToSnakeCase()}".debug() + ":$version"
//            is ReleaseModules -> "${Modules.groupId}:${property.name.camelToSnakeCase()}".release() + ":$version"
//            else -> throw UnsupportedOperationException()
//        }
//    }
//
//    private operator fun getValue(thisRef: ModulesWithoutVersion, property: KProperty<*>): String {
//        return when (thisRef) {
//            is DebugModulesWithoutVersion -> "${Modules.groupId}:${property.name.camelToSnakeCase()}".debug()
//            is ReleaseModulesWithoutVersion -> "${Modules.groupId}:${property.name.camelToSnakeCase()}".release()
//            else -> throw UnsupportedOperationException()
//        }
//    }

    val material get() = "com.google.android.material:material:${versionScoped.material}"
    val junit4 get() = "junit:junit:${versionScoped.junit4}"
    val gson get() = "com.google.code.gson:gson:${versionScoped.gson}"
    val glide get() = "com.github.bumptech.glide:glide:${versionScoped.glide}"

    private companion object {
        private fun String.debug() = "$this-debug"
        private fun String.release() = "$this-release"

        private val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
        private val snakeRegex = "_[a-zA-Z]".toRegex()

        // String extensions
        private fun String.camelToSnakeCase(): String {
            return camelRegex.replace(this) {
                "_${it.value}"
            }.toLowerCase()
        }

        private fun String.snakeToLowerCamelCase(): String {
            return snakeRegex.replace(this) {
                it.value.replace("_", "")
                    .toUpperCase()
            }
        }
    }
}