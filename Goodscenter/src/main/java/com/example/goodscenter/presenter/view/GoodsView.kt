package com.example.goodscenter.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Goods

interface GoodsView :BaseView{

    fun onGoods(data : MutableList<Goods>?)
}