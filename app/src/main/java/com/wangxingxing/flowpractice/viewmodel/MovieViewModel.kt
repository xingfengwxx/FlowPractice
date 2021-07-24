package com.wangxingxing.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.flowpractice.model.Movie
import com.wangxingxing.flowpractice.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2021/7/23 14:52
 * email : 1099420259@qq.com
 * description :
 */
class MovieViewModel : ViewModel() {

    private val movies by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 8,
                prefetchDistance = 1,
                initialLoadSize = 16
            ),
            pagingSourceFactory = {MoviePagingSource()}
        ).flow.cachedIn(viewModelScope)
    }

    fun loadMovie() : Flow<PagingData<Movie>> = movies
}