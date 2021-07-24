package com.wangxingxing.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.wangxingxing.flowpractice.databinding.MovieLoadmoreBinding
import com.wangxingxing.flowpractice.ui.BindingViewHolder

/**
 * author : 王星星
 * date : 2021/7/23 15:55
 * email : 1099420259@qq.com
 * description :
 */
class MovieLoadMoreAdapter(private val context: Context) : LoadStateAdapter<BindingViewHolder>() {
    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        val binding = MovieLoadmoreBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
}