package com.example.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by baixiao on 2019/4/2.
 */

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesActivity():Context{
         return context
    }
}