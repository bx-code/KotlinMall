package com.example.usercenter.ui.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.ui.presenter.view.ResetPwdView
import javax.inject.Inject


class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>(){

    @Inject
    lateinit var userservice : UserServiceImpl

    fun resetPwd(mobile : String,mpwd : String){
            mView.showLoading()
            userservice.resetpwd(mobile,mpwd).execute(object : BaseSubscriber<String>(mView){
                override fun onNext(t: String) {
                    super.onNext(t)
                    mView.hideLoading()
                    mView.onResetQueser(t.toString())

                }
                override fun onError(e: Throwable?) {
                    super.onError(e)
                }

            },lifecycleProvider)
    }

}