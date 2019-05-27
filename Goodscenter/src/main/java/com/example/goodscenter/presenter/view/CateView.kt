package com.example.goodscenter.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView
import com.example.goodscenter.data.protocal.CartGoods

interface  CateView : BaseView{

    fun toCartDate(date : Int)
}