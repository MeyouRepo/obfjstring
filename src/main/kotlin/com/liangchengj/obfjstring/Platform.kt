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


    @JvmStatic
    fun isUnity(): Boolean {
        return try {
            Class.forName("com.unity3d.player.UnityPlayer")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

}