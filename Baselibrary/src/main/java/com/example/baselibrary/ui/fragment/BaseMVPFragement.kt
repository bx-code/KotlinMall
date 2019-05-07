package com.example.baselibrary.ui.fragment

import android.os.Bundle
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.injection.module.LifecyclerModule
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.baselibrary.ui.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

open abstract  class BaseMVPFragement<T:BasePresenter<*>> : BaseFragment(),BaseView{

    @Inject
    lateinit var  mPresenter: T
    lateinit var activityComponent: ActivityComponent

    override fun showLoading() {
    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniecActivitytion()
        initDaggerTwo()
    }

    abstract fun initDaggerTwo()


    private fun iniecActivitytion() {
          activityComponent = DaggerActivityComponent.builder().appComponent((activity!!.application as BaseApplication).appComponent)
                  .lifecyclerModule(LifecyclerModule(this))
                  .activityModule(ActivityModule(activity!!)).build()
    }
}