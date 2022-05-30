package cn.tinyhai.android.config

import com.android.build.gradle.BaseExtension
import cn.tinyhai.android.dsl.PublishScoped
import cn.tinyhai.android.extentions.tinyhai
import org.gradle.api.Project
import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.jvm.tasks.Jar
import java.net.URI

internal object PublishConfigImpl : Configuration {
    override fun configure(project: Project) {
        val publishConfig = project.tinyhai.publishConfig
        val enablePublish = publishConfig.enabled
        if (!enablePublish) {
            return
        }
        val hasMavenPublish = project.plugins.hasPlugin("maven-publish")

        if (enablePublish && !hasMavenPublish) {
            project.plugins.apply("maven-publish")
        }
        val publish = project.extensions.findByType(PublishingExtension::class.java) ?: return
        publish.publishing(project, publishConfig)
    }

    private fun PublishingExtension.publishing(project: Project, publishConfig: PublishScoped) {
        val isAndroidLibrary = project.plugins.hasPlugin("com.android.library")
        val isAndroidApp = project.plugins.hasPlugin("com.android.application")
        if (isAndroidApp) {
            // Android应用入口无需发布
            return
        }
        repositories.apply {
            mavenLocal()
//            maven { mavenRepository ->
//                mavenRepository.url =
//                    URI.create("https://github.com/api/v4/projects/${publishConfig.gitlabProjectId}/packages/maven")
//                mavenRepository.credentials(HttpHeaderCredentials::class.java) {
//                    it.name = "Private-Token"
//                    it.value = publishConfig.privateToken
//                }
//                mavenRepository.authentication {
//                    it.register("header", HttpHeaderAuthentication::class.java)
//                }
//            }
        }
        publications.apply {
            if (isAndroidLibrary) {
                val android = project.extensions.getByType(BaseExtension::class.java)
                register("release", MavenPublication::class.java) {
                    it.from(project.components.findByName("release"))
                    it.groupId = publishConfig.groupId
                    it.artifactId = publishConfig.artifactId + "-release"
                    it.version = publishConfig.version
                }
                register("debug", MavenPublication::class.java) {
                    it.from(project.components.findByName("debug"))
                    val sources = project.tasks.register("source", Jar::class.java) { jar ->
                        jar.from(android.sourceSets.getByName("main").java.srcDirs)
                        jar.archiveClassifier.set("sources")
                    }

                    it.artifact(sources)
                    it.groupId = publishConfig.groupId
                    it.artifactId = publishConfig.artifactId + "-debug"
                    it.version = publishConfig.version
                }
            } else {
                register("tinyhai", MavenPublication::class.java) {
                    it.from(project.components.getByName("java"))
                    it.groupId = publishConfig.groupId
                    it.artifactId = publishConfig.artifactId
                    it.version = publishConfig.version
                }
            }
        }
    }
}