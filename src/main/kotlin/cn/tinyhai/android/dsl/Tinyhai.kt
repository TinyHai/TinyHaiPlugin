package cn.tinyhai.android.dsl

import cn.tinyhai.android.PLUGIN_MODULE_NAME
import cn.tinyhai.android.PLUGIN_ROOT_NAME
import cn.tinyhai.android.config.AndroidConfigImpl
import cn.tinyhai.android.config.DefaultCommonConfig
import cn.tinyhai.android.dependency.Deps
import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.util.ConfigureUtil

interface TinyhaiScoped {
    val deps: Deps

    val dependencyVersion: VersionScoped
    val publishConfig: PublishScoped
    val android: AndroidScoped
    val dependencySubstitution: SubstitutionScoped

    fun dependencyVersion(closure: Closure<*>) {
        ConfigureUtil.configure(closure, dependencyVersion as MutableVersionScoped)
    }

    fun dependencyVersion(block: MutableVersionScoped.() -> Unit) = block(dependencyVersion as MutableVersionScoped)

    fun publishConfig(closure: Closure<*>) {
        ConfigureUtil.configure(closure, publishConfig as MutablePublishScoped)
    }

    fun publishConfig(block: MutablePublishScoped.() -> Unit) = block(publishConfig as MutablePublishScoped)

    fun android(closure: Closure<*>) {
        ConfigureUtil.configure(closure, android as MutableAndroidScoped)
    }

    fun android(block: MutableAndroidScoped.() -> Unit) = block(android as MutableAndroidScoped)

    fun dependencySubstitution(closure: Closure<*>) {
        ConfigureUtil.configure(closure, dependencySubstitution)
    }

    fun dependencySubstitution(block: SubstitutionScoped.() -> Unit) {
        block(dependencySubstitution)
    }

    companion object {
        fun setup(project: Project) {
            val impl = TinyhaiScopedImpl(project)
            project.extensions.add(TinyhaiScoped::class.java, PLUGIN_MODULE_NAME, impl)
        }

        fun obtain(project: Project): TinyhaiScoped {
            return project.extensions.getByType(TinyhaiScoped::class.java)
                ?: throw IllegalStateException("$PLUGIN_MODULE_NAME 未安装")
        }
    }
}

internal class TinyhaiScopedImpl(
    private val project: Project,
) : TinyhaiScoped {

    override val dependencyVersion: VersionScoped = VersionScoped()

    override val deps: Deps = Deps(dependencyVersion)

    override val publishConfig: PublishScoped = PublishScoped()

    override val android: AndroidScoped = AndroidScoped()

    override val dependencySubstitution: SubstitutionScoped = SubstitutionScoped()

    override fun android(block: MutableAndroidScoped.() -> Unit) =
        block(android as MutableAndroidScoped).also {
            project.let {
                AndroidConfigImpl(
                    android,
                    DefaultCommonConfig
                ).configure(it)
            }
        }

    override fun android(closure: Closure<*>) {
        closure.call(android as MutableAndroidScoped).also {
            AndroidConfigImpl(android, DefaultCommonConfig).configure(project)
        }
    }
}

interface TinyhaiRootScoped {

    var repositories: RepositoryHandler.() -> Unit

    fun repositories(block: RepositoryHandler.() -> Unit) {
        repositories = block
    }

    fun repositories(closure: Closure<*>) {
        repositories = {
            ConfigureUtil.configure(closure, this)
        }
    }

    val publishScoped: PublishScoped

    fun publishScoped(closure: Closure<*>) {
        ConfigureUtil.configure(closure, publishScoped as MutablePublishScoped)
    }

    companion object {
        fun setup(project: Project): TinyhaiRootScoped {
            val impl = TinyhaiRootScopedImpl()
            project.extensions.add(
                TinyhaiRootScoped::class.java,
                PLUGIN_ROOT_NAME,
                impl
            )
            return impl
        }

        fun obtain(project: Project): TinyhaiRootScoped {
            return project.extensions.getByType(TinyhaiRootScoped::class.java)
                ?: throw IllegalStateException("$PLUGIN_ROOT_NAME 未安装")
        }
    }
}

internal class TinyhaiRootScopedImpl : TinyhaiRootScoped {
    override var repositories: RepositoryHandler.() -> Unit = {}

    override val publishScoped: PublishScoped = PublishScoped()
}