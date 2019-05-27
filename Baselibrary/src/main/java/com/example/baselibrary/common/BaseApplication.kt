package com.example.baselibrary.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.injection.component.AppComponent
import com.example.baselibrary.injection.component.DaggerAppComponent
import com.example.baselibrary.injection.module.AppModule

/**
 * Created by baixiao on 2019/4/2.
 */

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context = this

        ARouter.openLog() //ARouter 打印日志
        ARouter.openDebug() //开启调试模式
        ARouter.init(this)
    }

    private fun initAppInjection() {
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}