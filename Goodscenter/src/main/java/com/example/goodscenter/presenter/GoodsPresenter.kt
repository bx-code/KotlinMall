package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.goodscenter.presenter.view.GoodsView
import com.example.goodscenter.service.impl.GoodsServiceImpl
import com.kotlin.goods.data.protocol.Goods
import javax.inject.Inject

class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>() {

    @Inject
    lateinit var goodsServiceImpl: GoodsServiceImpl

    fun GetGoodLists(categoryId: Int, pageNo: Int){
        goodsServiceImpl.toGoodsList(categoryId, pageNo).execute(object  :
                    BaseSubscriber<MutableList<Goods>?>(mView){

            override fun onNext(t: MutableList<Goods>?) {
                super.onNext(t)
                mView.onGoods(t)
            }
        },lifecycleProvider)
    }

    fun GetGoodListsByKeyWord(keyword: String, pageNo: Int){
        goodsServiceImpl.GoodsKeyWord(keyword, pageNo).execute(object  :
                BaseSubscriber<MutableList<Goods>?>(mView){

            override fun onNext(t: MutableList<Goods>?) {
                super.onNext(t)
                mView.onGoods(t)
            }
        },lifecycleProvider)
    }
}