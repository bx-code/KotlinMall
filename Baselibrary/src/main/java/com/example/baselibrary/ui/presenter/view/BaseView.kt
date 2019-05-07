package com.example.baselibrary.ui.presenter.view

/**
 * Created by baixiao on 2019/3/30.
 * MVP V层 封装基类
 */
interface  BaseView{

     fun showLoading()
     fun hideLoading()
     fun onError()
}