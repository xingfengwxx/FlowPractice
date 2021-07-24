package com.wangxingxing.flowpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.wangxingxing.flowpractice.adapter.MovieAdapter
import com.wangxingxing.flowpractice.adapter.MovieLoadMoreAdapter
import com.wangxingxing.flowpractice.databinding.FragmentMovieBinding
import com.wangxingxing.flowpractice.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2021/7/23 14:45
 * email : 1099420259@qq.com
 * description :
 */
class MovieFragment : Fragment() {

    private val viewModel by viewModels<MovieViewModel>()

    private val mBinding: FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
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
            val movieAdapter = MovieAdapter(it)

            mBinding.apply {
                recyclerView.adapter = movieAdapter.withLoadStateFooter(MovieLoadMoreAdapter(it))
                swipeRefreshLayout.setOnRefreshListener {
                    movieAdapter.refresh()
                }
            }

            lifecycleScope.launchWhenCreated {
                viewModel.loadMovie().collectLatest {
                    movieAdapter.submitData(it)
                }
            }

            lifecycleScope.launchWhenCreated {
                movieAdapter.loadStateFlow.collectLatest { state ->
                    mBinding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading
                }
            }
        }


    }
}