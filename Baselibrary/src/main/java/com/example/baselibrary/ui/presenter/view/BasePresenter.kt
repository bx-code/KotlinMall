package com.example.baselibrary.ui.presenter.view

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

open  class BasePresenter<T : BaseView>{

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider :LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork():Boolean {
        return NetWorkUtils.isNetWorkAvailable(context)
    }
}