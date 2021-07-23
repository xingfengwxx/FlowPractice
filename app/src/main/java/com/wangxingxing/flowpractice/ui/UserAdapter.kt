package com.wangxingxing.flowpractice.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wangxingxing.flowpractice.databinding.ItemUserBinding
import com.wangxingxing.flowpractice.db.User

/**
 * author : 王星星
 * date : 2021/7/21 19:47
 * email : 1099420259@qq.com
 * description :
 */
class UserAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>() {

    private val mData = ArrayList<User>()

    fun setData(data: List<User>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = mData[position]
        //转型

        val binding = holder.binding as ItemUserBinding
        binding.text.text = "${item.uid}, ${item.firstName}, ${item.lastName}"
    }

    override fun getItemCount() = mData.size
}