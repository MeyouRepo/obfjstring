package me.liangchengj.obfjstring

object TextUtils {
    /**
     * Returns true if the string is null or 0-length.
     *
     * @param charSequence the string to be examined
     * @return true if str is null or zero length
     */
    @JvmStatic
    fun isEmpty(charSequence: CharSequence): Boolean {
        return charSequence.isEmpty()
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param charSequence the string to be examined
     * @return true if str is null or zero length
     */
    @JvmStatic
    fun isEmptyAfterTrim(charSequence: CharSequence): Boolean {
        return charSequence.isEmpty() || charSequence.trim().isEmpty()
    }
}