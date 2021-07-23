package com.wangxingxing.flowpractice.utils

import java.io.InputStream
import java.io.OutputStream

/**
 * author : 王星星
 * date : 2021/7/22 11:11
 * email : 1099420259@qq.com
 * description :
 */

inline fun InputStream.copyTo(out: OutputStream, bufferSize: Int = DEFAULT_BUFFER_SIZE, progress: (Long) -> Unit): Long {
    var bytesCopied: Long = 0
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)

        progress(bytesCopied)
    }
    return bytesCopied
}