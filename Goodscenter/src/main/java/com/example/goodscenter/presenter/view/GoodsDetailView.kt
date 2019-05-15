package com.example.goodscenter.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Goods

interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)
    //加入购物车
    fun onAddCartResult(result: Int)

}