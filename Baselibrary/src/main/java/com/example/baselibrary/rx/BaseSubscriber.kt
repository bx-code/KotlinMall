package com.example.baselibrary.rx

import com.example.baselibrary.ui.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by baixiao on 2019/3/30.
 * RxAndroid 自定义订阅者 Subscriber
 */

open class BaseSubscriber<T>(val mView_suber : BaseView) :Subscriber<T>(){

    override fun onNext(t: T) {

    }

    override fun onCompleted() {
        mView_suber.hideLoading()
    }

    override fun onError(e: Throwable?) {
        mView_suber.hideLoading()
        if (e is BaseException){
            mView_suber.onError()
        }
    }

}