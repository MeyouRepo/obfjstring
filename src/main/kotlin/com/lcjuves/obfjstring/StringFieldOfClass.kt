package com.lcjuves.obfjstring

/**
 * Created at 2021/6/9 17:48.
 *
 * @author Liangcheng Juves
 */
data class StringFieldOfClass(
    var name: String,
    var value: String?
) {
    companion object {
        const val STRING_SIG: String = "Ljava/lang/String;"
        const val STRING_DESC: String = STRING_SIG
    }
}