package com.liangchengj.obfjstring

/**
 * Created at 2021/6/10 10:28.
 *
 * @author Liangcheng Juves
 */
object Platform {
    @JvmStatic
    fun isAndroid(): Boolean {
        return try {
            Class.forName("android.os.Process")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}