package com.lcjuves.obfjstring.io

import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream

/**
 * Created at 2021/6/8 18:22
 *
 * @author Liangcheng Juves
 */
object Stream {

    private const val EOF = -1;
    private const val DEFAULT_BUFFER_SIZE = 1024;

    @JvmStatic
    fun readAndWrite(bufferSize: Int, inputStream: InputStream?, outputStream: OutputStream?) {
        val bytes = ByteArray(bufferSize)
        var len: Int
        inputStream.use { `in` ->
            outputStream.use { out ->
                while (`in`?.read(bytes).also { len = it!! } != EOF) {
                    out?.write(bytes, 0, len)
                    out?.flush()
                }
            }
        }
        close(outputStream)
        close(inputStream)
    }

    @JvmStatic
    fun readAndWrite(inputStream: InputStream?, outputStream: OutputStream?) {
        readAndWrite(DEFAULT_BUFFER_SIZE, inputStream, outputStream)
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