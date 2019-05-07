package com.example.usercenter.ui.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView

/**
 * Created by baixiao on 2019/3/30.
 * 最终在UI线程中实现的方法 控制UI层的变化与展示
 */

interface RegisterVIew : BaseView{

    fun ondata(json : String)

}