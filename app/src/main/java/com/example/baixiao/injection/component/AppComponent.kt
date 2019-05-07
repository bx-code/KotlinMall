package com.example.baixiao.injection.component

import com.example.baixiao.injection.module.AppModule
import com.example.baixiao.ui.MainActivity
import dagger.Component

/**
 * Created by baixiao on 2019/4/2.
 */

@Component (modules = arrayOf(AppModule::class))
interface AppComponent{
    fun Inject(activity: MainActivity)
}