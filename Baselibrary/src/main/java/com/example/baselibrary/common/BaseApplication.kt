package com.example.baselibrary.common

import android.app.Application
import android.content.Context
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
    }

    private fun initAppInjection() {
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}