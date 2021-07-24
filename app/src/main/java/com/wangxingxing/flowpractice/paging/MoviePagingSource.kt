package com.wangxingxing.flowpractice.paging

import android.util.Log
import androidx.paging.PagingSource
import com.blankj.utilcode.util.LogUtils
import com.wangxingxing.flowpractice.model.Movie
import com.wangxingxing.flowpractice.net.MovieApi
import com.wangxingxing.flowpractice.net.RetrofitClient
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 * author : 王星星
 * date : 2021/7/23 14:57
 * email : 1099420259@qq.com
 * description :
 */
class MoviePagingSource : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        delay(2000)
        val currentPage = params.key ?: 1
        val pageSize = params.loadSize
        val movies = RetrofitClient.createApi(MovieApi::class.java).getMovies(currentPage, pageSize)
        LogUtils.d("currentPage: $currentPage ,pageSize: $pageSize")

        var prevKey: Int? = null
        var nextKey: Int? = null

        val realPageSize = 8
        val initialLoadSize = 8
        if (currentPage == 1) {
            prevKey = null
            nextKey = initialLoadSize / realPageSize + 1
        } else {
            prevKey = currentPage - 1
            nextKey = if (movies.hasMore) currentPage + 1 else null
        }
        LogUtils.d("prevKey: $prevKey ,nextKey:$nextKey")

        return try {
            LoadResult.Page(
                data = movies.movieList,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }
}