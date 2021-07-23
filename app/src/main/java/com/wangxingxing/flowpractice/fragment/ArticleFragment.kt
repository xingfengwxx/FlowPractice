package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.flowpractice.databinding.FragmentArticleBinding
import com.wangxingxing.flowpractice.ui.ArticleAdapter
import com.wangxingxing.flowpractice.viewmodel.ArticleViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect

/**
 * author : 王星星
 * date : 2021/7/22 14:37
 * email : 1099420259@qq.com
 * description : 关键字搜索. Flow + Retrofit.
 */
class ArticleFragment : Fragment() {

    private val TAG = "ArticleFragment"

    private val mBinding: FragmentArticleBinding by lazy {
        FragmentArticleBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<ArticleViewModel>()

    //定义一个扩展函数
    //监听输入框输入的文字，发送给Flow
    private fun TextView.textWatcherFlow(): Flow<String> = callbackFlow {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                offer(s.toString())
            }

        }

        //给TextView添加监听
        addTextChangedListener(textWatcher)
        //当Flow关闭时，移除监听
        awaitClose { removeTextChangedListener(textWatcher) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            //根据输入的关键词，请求网络
            mBinding.etSearch.textWatcherFlow().collect {
                Log.d(TAG, "onActivityCreated: collect keywords: $it")
                viewModel.searchArticles(it)
            }
        }

        context?.let {
            val adapter = ArticleAdapter(it)
            mBinding.recyclerView.adapter = adapter
            viewModel.articles.observe(viewLifecycleOwner, { articles ->
                adapter.setData(articles)
            })
        }
    }
}