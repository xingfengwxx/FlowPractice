package com.wangxingxing.flowpractice.net

import com.wangxingxing.flowpractice.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author : 王星星
 * date : 2021/7/23 15:08
 * email : 1099420259@qq.com
 * description :
 */
interface MovieApi {

    @GET("pkds.do")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Movies
}