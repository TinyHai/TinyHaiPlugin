package cn.tinyhai.android.dsl

interface PublishScoped {
    /**
     * 发布到远程仓库时对应到项目id
     */
    val gitlabProjectId: Int

    /**
     * 是否开启发布
     */
    val enabled: Boolean

    /**
     * 如果要发布到远程仓库，需要配置gitlab/github对应对token
     */
    val privateToken: String

    /**
     * 发布的包的groupId
     */
    val groupId: String

    /**
     * 发布的包的version
     */
    val version: String

    /**
     * 发布的包的artifactId
     * 一般为项目名称
     */
    val artifactId: String

    companion object {
        internal operator fun invoke(): PublishScoped {
            return PublishScopedImpl()
        }
    }
}

interface MutablePublishScoped : PublishScoped {
    override var gitlabProjectId: Int
    override var enabled: Boolean
    override var privateToken: String
    override var groupId: String
    override var version: String
    override var artifactId: String
}

class PublishScopedImpl : MutablePublishScoped {
    override var gitlabProjectId: Int = 254
    override var enabled: Boolean = false
    override var privateToken: String = "Timz9xZcsHw_jEsU9xXq"
    override var groupId: String = "cn.tinyhai.android"
    override var version: String = "1.0.0"
    override var artifactId: String = "undefined"
}