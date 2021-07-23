package com.wangxingxing.flowpractice.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : 王星星
 * date : 2021/7/22 15:08
 * email : 1099420259@qq.com
 * description :
 */
object RetrofitClient {

    private val instance: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("http://192.168.1.4:8080/kotlinstudyserver/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApi: ArticleApi by lazy {
        instance.create(ArticleApi::class.java)
    }
}