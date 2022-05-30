package cn.tinyhai.android.dsl

interface AndroidScoped {
    /**
     * 是否开启默认配置
     * 若为true 则会调用{@link cn.tinyhai.android.modules.plugin.config.AndroidConfigImpl#commonApply}
     */
    val applyDefault: Boolean

    /**
     * 是否开启viewBinding
     */
    val viewBinding: Boolean

    /**
     * 是否开启dataBinding
     */
    val dataBinding: Boolean

    /**
     * 是否使用compose
     */
    val useCompose: Boolean

    companion object {
        internal operator fun invoke(): AndroidScoped = AndroidScopedImpl()
    }
}

interface MutableAndroidScoped : AndroidScoped {
    override var applyDefault: Boolean
    override var viewBinding: Boolean
    override var dataBinding: Boolean
    override var useCompose: Boolean
}

internal class AndroidScopedImpl : MutableAndroidScoped {
    override var applyDefault: Boolean = false
    override var viewBinding: Boolean = false
    override var dataBinding: Boolean = false
    override var useCompose: Boolean = false
}