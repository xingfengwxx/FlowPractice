package com.wangxingxing.flowpractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wangxingxing.flowpractice.common.Event
import com.wangxingxing.flowpractice.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * author : 王星星
 * date : 2021/7/22 20:09
 * email : 1099420259@qq.com
 * description :
 */
class SharedFlowViewModel : ViewModel() {

    private lateinit var job: Job

    fun startRefresh() {
        job = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }
    }

    fun stopRefresh() {
        job.cancel()
    }
}