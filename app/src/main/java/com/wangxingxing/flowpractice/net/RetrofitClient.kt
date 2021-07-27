package com.wangxingxing.flowpractice.net

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * author : 王星星
 * date : 2021/7/22 15:08
 * email : 1099420259@qq.com
 * description :
 */
object RetrofitClient {

    private const val TAG = "RetrofitClient"

    // http://192.168.1.4:8080/kotlinstudyserver/
    // http://192.168.1.4:8080/pagingserver_war/

    // https://www.wanandroid.com/article/list/0/json

    private val BASE_URL = "http://121.4.214.140:8080/"

    private val instance: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d(TAG, it)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleApi: ArticleApi by lazy {
        instance.create(ArticleApi::class.java)
    }

    fun <T> createApi(clazz: Class<T>): T {
        return instance.create(clazz) as T
    }
}