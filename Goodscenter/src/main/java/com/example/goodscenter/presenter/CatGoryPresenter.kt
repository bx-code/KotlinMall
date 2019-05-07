package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.goodscenter.presenter.view.CatGoryView
import com.example.goodscenter.service.impl.CateGoryServiceImpl
import com.kotlin.goods.data.protocol.Category
import javax.inject.Inject


class CatGoryPresenter @Inject constructor(): BasePresenter<CatGoryView>(){

    @Inject
    lateinit var CatGoryImpl : CateGoryServiceImpl

    fun  toCatGoryInfo(catid: Int){
        CatGoryImpl.intoCateGory(catid).execute(object : BaseSubscriber<MutableList<Category>?>(mView){
            override fun onNext(t: MutableList<Category>?) {
                super.onNext(t)
                mView.toData(t)
            }

            override fun onError(e: Throwable?) {
                super.onError(e)
                mView.ErrorData(e!!)
            }
        },lifecycleProvider)
    }

}