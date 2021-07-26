package com.wangxingxing.flowpractice.net

import com.wangxingxing.flowpractice.model.WAArticle
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author : 王星星
 * date : 2021/7/26 15:12
 * email : 1099420259@qq.com
 * description : WanAndroid开放API
 */
interface WanAndroidApi {

    @GET("/article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): WAArticle
}