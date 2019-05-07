package com.example.baselibrary.injection.component

import android.content.Context
import com.example.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by baixiao on 2019/4/2.
 */

@Singleton
@Component (modules = arrayOf(AppModule::class))
interface AppComponent {

    fun context(): Context
}