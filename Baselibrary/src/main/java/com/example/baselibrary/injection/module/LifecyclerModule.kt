package com.example.baselibrary.injection.module

import android.app.Activity
import com.example.baselibrary.injection.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by baixiao on 2019/4/2.
 * 使用Dagger2 注入RxLifecycle 创建@Module @Provides
 */

@Module
class LifecyclerModule(private val lifecycleProvider : LifecycleProvider<*>) {

    @Provides
    fun providesActivity(): LifecycleProvider<*>{
         return lifecycleProvider
    }
}