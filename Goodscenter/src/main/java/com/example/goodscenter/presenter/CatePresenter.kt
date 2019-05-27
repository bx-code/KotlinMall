package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.goodscenter.presenter.view.CateView
import com.example.goodscenter.service.impl.CartServiceImpl
import javax.inject.Inject

class CatePresenter @Inject constructor(): BasePresenter<CateView>() {

    @Inject
    lateinit var  CartImpl : CartServiceImpl

    fun dataCate(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String){
            CartImpl.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku).execute(object :BaseSubscriber<Int>(mView){

                override fun onNext(t: Int) {
                    super.onNext(t)
                    mView.toCartDate(t)
                }
            },lifecycleProvider)
    }
}