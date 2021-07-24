package com.wangxingxing.flowpractice

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils


/**
 * author : 王星星
 * date : 2021/7/23 15:21
 * email : 1099420259@qq.com
 * description :
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Utils.init(this)
        initLog()
    }

    private fun initLog() {
        LogUtils.getConfig()
            .setLogSwitch(true)
            .setGlobalTag("wxx")
            .setBorderSwitch(true)
    }
}