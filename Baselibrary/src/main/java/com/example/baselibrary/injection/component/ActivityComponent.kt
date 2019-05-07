package com.example.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.example.baselibrary.injection.ActivityScope
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.injection.module.AppModule
import com.example.baselibrary.injection.module.LifecyclerModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

/**
 * Created by baixiao on 2019/4/2.
 */

@ActivityScope
@Component (dependencies = arrayOf(AppComponent::class),
            modules = arrayOf(ActivityModule::class,LifecyclerModule::class))
interface ActivityComponent {

    fun activity(): Activity
    fun context():Context
    fun onlifecycler(): LifecycleProvider<*>
}