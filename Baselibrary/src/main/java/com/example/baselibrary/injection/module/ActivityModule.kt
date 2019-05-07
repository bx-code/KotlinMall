package com.example.baselibrary.injection.module

import android.app.Activity
import com.example.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by baixiao on 2019/4/2.
 */

@Module
class ActivityModule(private val activity:Activity) {

    @Provides
    fun providesActivity():Activity{
         return activity
    }
}