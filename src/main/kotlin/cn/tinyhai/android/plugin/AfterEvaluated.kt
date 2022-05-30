package cn.tinyhai.android.plugin

import cn.tinyhai.android.config.DependencySubstitutionConfig
import cn.tinyhai.android.config.PublishConfigImpl
import org.gradle.api.Project

object AfterEvaluated : (Project) -> Unit {
    override fun invoke(project: Project) {
        // 配置android library发布到本地/远程maven仓库
        PublishConfigImpl.configure(project)
        DependencySubstitutionConfig.configure(project)
    }
}

