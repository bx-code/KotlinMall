package com.example.usercenter.ui.presenter

import android.widget.Toast
import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.ui.presenter.view.LoginView
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

class LoginPresenter @Inject constructor(): BasePresenter<LoginView>(){

    @Inject
    lateinit var userServiceImpl: UserServiceImpl

    fun login(name:String,iphone:String){
        if(!checkNetWork()){
            Toast.makeText(context,"当前无网络链接",Toast.LENGTH_SHORT).show()
            return
        }
        mView.showLoading()
        userServiceImpl.Login(name,iphone).execute(object : BaseSubscriber<String>(mView){
                    override fun onNext(t: String) {
                        super.onNext(t)
                        mView.ondata("${t}成功")
                    }

            override fun onError(e: Throwable?) {
                super.onError(e)
                mView.ondata("登陆失败${e.toString()}")
                e?.printStackTrace()
            }

                },lifecycleProvider)
    }


}