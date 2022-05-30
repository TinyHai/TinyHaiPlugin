package cn.tinyhai.android.config

import org.gradle.api.Project

interface Configuration {
    fun configure(project: Project)
}