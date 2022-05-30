package cn.tinyhai.android.dsl

interface SubstitutionScoped {

    val isEnabled: Boolean

    val substitutions: List<Pair<String, String>>

    fun substitute(pair: Pair<String, String>)

    fun substitute(module: String, project: String) = substitute(module to project)

    fun enabled(isEnabled: Boolean)

    companion object {
        operator fun invoke(): SubstitutionScoped {
            return SubstitutionScopedImpl()
        }
    }
}

interface MutableSubstitutionScoped : SubstitutionScoped {
    override var isEnabled: Boolean
}

internal class SubstitutionScopedImpl : MutableSubstitutionScoped {

    private val subs = ArrayList<Pair<String, String>>()

    override var isEnabled: Boolean = false

    override val substitutions: List<Pair<String, String>> get() = subs

    override fun substitute(pair: Pair<String, String>) {
        subs.add(pair)
    }

    override fun enabled(isEnabled: Boolean) {
        this.isEnabled = isEnabled
    }
}