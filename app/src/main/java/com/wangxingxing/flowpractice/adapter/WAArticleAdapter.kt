package com.wangxingxing.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.blankj.utilcode.util.TimeUtils
import com.wangxingxing.flowpractice.databinding.ItemWanAndroidBinding
import com.wangxingxing.flowpractice.model.WAArticle
import com.wangxingxing.flowpractice.ui.BindingViewHolder

/**
 * author : 王星星
 * date : 2021/7/26 16:56
 * email : 1099420259@qq.com
 * description :
 */
class WAArticleAdapter(private val context: Context) :
    PagingDataAdapter<WAArticle.Data.Info, BindingViewHolder>(object : DiffUtil.ItemCallback<WAArticle.Data.Info>() {
        override fun areItemsTheSame(
            oldItem: WAArticle.Data.Info,
            newItem: WAArticle.Data.Info
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WAArticle.Data.Info,
            newItem: WAArticle.Data.Info
        ): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            val binding = holder.binding as ItemWanAndroidBinding
            binding.tvAuthor.text = it.shareUser
            binding.tvTime.text = TimeUtils.millis2String(it.publishTime, "yyyy-MM-dd HH:mm")
            binding.tvTitle.text = it.title
            binding.tvCategory.text = "${it.chapterName}/${it.superChapterName}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemWanAndroidBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
}