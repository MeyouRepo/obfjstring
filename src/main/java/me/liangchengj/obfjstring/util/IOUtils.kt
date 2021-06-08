package me.liangchengj.obfjstring.util

import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream

/**
 * Created at 2021/6/8 18:22.
 *
 * @author Liangcheng Juves
 */
object IOUtils {

    @JvmStatic
    fun readAndWrite(inputStream: InputStream?, outputStream: OutputStream?) {
        val bytes = ByteArray(1024)
        var len = 0
        inputStream.use { `in` ->
            outputStream.use { out ->
                while (`in`?.read(bytes).also {
                        if (it != null) {
                            len = it
                        }
                    } != -1) {
                    out?.write(bytes, 0, len)
                    out?.flush()
                }
            }
        }
        close(outputStream)
        close(inputStream)
    }


    @JvmStatic
    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (tr: Throwable) {
            // Ignore
        }
    }
}