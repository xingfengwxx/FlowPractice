package com.wangxingxing.flowpractice.download

import java.io.File

/**
 * author : 王星星
 * date : 2021/7/22 11:03
 * email : 1099420259@qq.com
 * description : 下载状态
 */
sealed class DownloadStatus {
    object None : DownloadStatus()
    data class Progress(val value: Int) : DownloadStatus()
    data class Error(val throwable: Throwable) : DownloadStatus()
    data class Done(val file: File) : DownloadStatus()
}
