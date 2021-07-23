package com.wangxingxing.flowpractice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * author : 王星星
 * date : 2021/7/22 15:37
 * email : 1099420259@qq.com
 * description :
 */
class NumberViewModel(app: Application) : AndroidViewModel(app) {

    val number = MutableStateFlow(0)

    fun increment() {
        number.value++
    }

    fun decrement() {
        number.value--
    }
}