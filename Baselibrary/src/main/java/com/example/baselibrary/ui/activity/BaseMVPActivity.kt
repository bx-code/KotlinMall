package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.injection.module.LifecyclerModule
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.baselibrary.ui.presenter.view.BaseView
import com.example.baselibrary.widgets.ProgressLoading
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 * 基于MVP模式基类 activity
 */

open abstract  class BaseMVPActivity<T:BasePresenter<*>> : BaseActivity(),BaseView{

    lateinit var activityComponent: ActivityComponent
    lateinit var progressLoading: ProgressLoading


    override fun showLoading() {
        progressLoading.showLoading()
    }

    override fun hideLoading() {
        progressLoading.hideLoading()
    }

    override fun onError() {

    }

    @Inject
    lateinit var  mPresenter: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniecActivitytion()
        initDaggerTwo()
        //初始化加载框
        progressLoading=  ProgressLoading.create(this)
    }

    /**
     * 创建抽象方法 子类继承处理注入Dagger2代码
     */
    abstract fun initDaggerTwo()

    private fun iniecActivitytion() {
          activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                  .lifecyclerModule(LifecyclerModule(this))
                  .activityModule(ActivityModule(this)).build()
    }
}