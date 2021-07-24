package com.wangxingxing.flowpractice.ui

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.wangxingxing.flowpractice.R

/**
 * author : 王星星
 * date : 2021/7/23 17:54
 * email : 1099420259@qq.com
 * description :
 */
class ImageViewBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, url: String) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(imageView).load(url).placeholder(R.drawable.ic_launcher_background).into(imageView)
            } else {
                imageView.setBackgroundColor(Color.GRAY)
            }
        }
    }
}