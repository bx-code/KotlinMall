package com.example.goodscenter.presenter

import android.view.View
import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.goodscenter.presenter.view.GoodsDetailView
import com.example.goodscenter.service.impl.CartServiceImpl
import com.example.goodscenter.service.impl.GoodsServiceImpl
import com.kotlin.goods.data.protocol.Goods
import javax.inject.Inject


class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsDetails : GoodsServiceImpl

    @Inject
    lateinit var cartService : CartServiceImpl

    //商品详情页面
    fun onGoodsDetail(id : Int){
        goodsDetails.getGoodsDetail(id).execute(object : BaseSubscriber<Goods>(mView){

            override fun onNext(t: Goods) {
                super.onNext(t)
                mView.onGetGoodsDetailResult(t)
            }
        },lifecycleProvider)
    }

    //添加购物车
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String){
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku).execute(object : BaseSubscriber<Int>(mView){

            override fun onNext(t: Int) {
                super.onNext(t)
                mView.onAddCartResult(t)
            }
        },lifecycleProvider)
    }

}