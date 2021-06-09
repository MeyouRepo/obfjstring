package me.liangchengj.obfjstring


data class StringFieldOfClass(
    var name: String?,
    var value: String?
) {
    companion object {
        const val STRING_SIG: String = "Ljava/lang/String;"
        const val STRING_DESC: String = STRING_SIG
    }
}