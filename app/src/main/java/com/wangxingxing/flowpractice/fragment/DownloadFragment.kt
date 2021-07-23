package com.wangxingxing.flowpractice.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.flowpractice.databinding.FragmentDownloadBinding
import com.wangxingxing.flowpractice.download.DownloadManager
import com.wangxingxing.flowpractice.download.DownloadStatus
import kotlinx.coroutines.flow.collect
import java.io.File

/**
 * author : 王星星
 * date : 2021/7/22 11:00
 * email : 1099420259@qq.com
 * description : 文件下载，进度更新
 */
class DownloadFragment : Fragment() {

    val URL = "https://cdn.jsdelivr.net/gh/myseil/BingWallpaper/BingImage/2021-07-22/OHR.MinokakeRocks_ZH-CN2474262090_UHD.jpg"

    private val TAG = "DownloadFragment"

    private val mBinding: FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file = File(getExternalFilesDir(null)?.path, "pic.jgp")
                Log.d(TAG, "onActivityCreated: " + file.absolutePath)
                DownloadManager.download(URL, file).collect { status ->
                    when (status) {
                        is DownloadStatus.Progress -> {
                            mBinding.apply {
                                progressBar.progress = status.value
                                tvProgress.text = "${status.value}%"
                            }
                        }
                        is DownloadStatus.Error -> {
                            Toast.makeText(context, "下载错误", Toast.LENGTH_SHORT).show()
                        }
                        is DownloadStatus.Done -> {
                            mBinding.apply {
                                progressBar.progress = 100
                                tvProgress.text = "100%"
                            }
                            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.d(TAG, "onActivityCreated: 下载失败")
                        }
                    }
                }
            }
        }
    }
}