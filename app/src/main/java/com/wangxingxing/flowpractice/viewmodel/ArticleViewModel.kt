package com.wangxingxing.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wangxingxing.flowpractice.model.Article
import com.wangxingxing.flowpractice.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Retrofit

/**
 * author : 王星星
 * date : 2021/7/22 14:46
 * email : 1099420259@qq.com
 * description :
 */
class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    private val TAG = "ArticleViewModel"

    val articles = MutableLiveData<List<Article>>()

    fun searchArticles(key: String) {
        viewModelScope.launch {
            flow {
                val list = RetrofitClient.articleApi.searchArticles(key)
                emit(list)
            }.flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                    Log.e(TAG, "searchArticles: ${e.message ?: "exception caught"}")
                }
                .collect {
                    Log.d(TAG, "searchArticles: collect articles: $it")
                    articles.setValue(it)
                }
        }
    }
}