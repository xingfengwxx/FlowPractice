package com.wangxingxing.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wangxingxing.flowpractice.model.WAArticle
import com.wangxingxing.flowpractice.paging.WAArticlePagingSource
import kotlinx.coroutines.flow.Flow

/**
 * author : 王星星
 * date : 2021/7/26 15:54
 * email : 1099420259@qq.com
 * description :
 */
class WAArticleViewModel : ViewModel() {

    private val articles by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {WAArticlePagingSource()}
        ).flow.cachedIn(viewModelScope)
    }

    fun loadArticle() : Flow<PagingData<WAArticle.Data.Info>> = articles
}