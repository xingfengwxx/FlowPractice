package com.wangxingxing.flowpractice.net

import com.wangxingxing.flowpractice.model.Article
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author : 王星星
 * date : 2021/7/22 14:51
 * email : 1099420259@qq.com
 * description :
 */
interface ArticleApi {

    //注意这里不写斜杠
    @GET("/kotlinstudyserver/article")
    suspend fun searchArticles(@Query("key") key: String): List<Article>
}