package cn.tinyhai.android.config

import cn.tinyhai.android.dsl.TinyhaiScoped
import org.gradle.api.Project

object DependencySubstitutionConfig : Configuration {
    override fun configure(project: Project) {
        val substitutionScoped = TinyhaiScoped.obtain(project).dependencySubstitution
        if (substitutionScoped.isEnabled.not()) {
            return
        }

        val subs = substitutionScoped.substitutions
        if (subs.isEmpty()) {
            return
        }

        project.configurations.all { configuration ->
            configuration.resolutionStrategy.apply {
                dependencySubstitution.apply {
                    subs.forEach {
                        substitute(module(it.first)).with(project(it.second))
                    }
                }
            }
        }
    }
}