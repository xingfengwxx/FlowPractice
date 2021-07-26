package com.wangxingxing.flowpractice.paging

import androidx.paging.PagingSource
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.flowpractice.model.WAArticle
import com.wangxingxing.flowpractice.net.RetrofitClient
import com.wangxingxing.flowpractice.net.WanAndroidApi
import java.lang.Exception

/**
 * author : 王星星
 * date : 2021/7/26 15:59
 * email : 1099420259@qq.com
 * description :
 */
class WAArticlePagingSource : PagingSource<Int, WAArticle.Data.Info>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WAArticle.Data.Info> {
        val currentPage = params.key ?: 0
        val pageSize = params.loadSize

        var articles: WAArticle? = null
        try {
            articles = RetrofitClient.createApi(WanAndroidApi::class.java).getArticle(currentPage)
            LogUtils.d("currentPage=$currentPage")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        var prevKey: Int? = null
        var nextKey: Int? = null

        val realPageSize = 20
        val initialLoadSize = 20
        if (currentPage == 0) {
            prevKey = null
            nextKey = initialLoadSize / realPageSize + 1
        } else {
            prevKey = currentPage - 1

            articles?.let {
                nextKey = if (it.data.curPage < it.data.pageCount) {
                    currentPage + 1
                } else {
                    null
                }
            }
        }

        return try {
            LoadResult.Page(
                data = articles!!.data.datas,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}