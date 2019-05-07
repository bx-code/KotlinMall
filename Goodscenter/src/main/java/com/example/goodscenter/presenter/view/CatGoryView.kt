package com.example.goodscenter.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Category


interface  CatGoryView : BaseView{

    fun toData(date : MutableList<Category>?)

    fun ErrorData(errorinfo :Throwable)

}