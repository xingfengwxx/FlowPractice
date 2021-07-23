package com.wangxingxing.flowpractice.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wangxingxing.flowpractice.databinding.ItemArticleBinding
import com.wangxingxing.flowpractice.model.Article

/**
 * author : 王星星
 * date : 2021/7/22 15:02
 * email : 1099420259@qq.com
 * description :
 */
class ArticleAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>() {

    private val mData = ArrayList<Article>()

    fun setData(data: List<Article>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = mData[position]
        //转型
        val binding = holder.binding as ItemArticleBinding
        binding.text.text = item.text
    }

    override fun getItemCount() = mData.size
}