package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wangxingxing.flowpractice.adapter.MovieLoadMoreAdapter
import com.wangxingxing.flowpractice.adapter.WAArticleAdapter
import com.wangxingxing.flowpractice.databinding.FragmentWanAndroidBinding
import com.wangxingxing.flowpractice.viewmodel.WAArticleViewModel
import kotlinx.coroutines.flow.collectLatest

/**
 * author : 王星星
 * date : 2021/7/26 15:18
 * email : 1099420259@qq.com
 * description :
 */
class WanAndroidFragment : Fragment() {

    private val viewModel by viewModels<WAArticleViewModel>()

    private val mBinding: FragmentWanAndroidBinding by lazy {
        FragmentWanAndroidBinding.inflate(layoutInflater)
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

        context?.let {
            val articleAdapter =  WAArticleAdapter(it)

            mBinding.apply {
                recyclerView.adapter = articleAdapter.withLoadStateFooter(MovieLoadMoreAdapter(it))
                swipeRefreshLayout.setOnRefreshListener {
                    articleAdapter.refresh()
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.loadArticle().collectLatest {
                    articleAdapter.submitData(it)
                }

                articleAdapter.loadStateFlow.collectLatest { state ->
                    mBinding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading
                }
            }


        }
    }
}