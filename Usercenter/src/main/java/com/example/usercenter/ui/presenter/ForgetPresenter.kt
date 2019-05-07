package com.example.usercenter.ui.presenter

import android.widget.Toast
import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.ui.presenter.view.ForgetView
import com.example.usercenter.ui.presenter.view.LoginView
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

class ForgetPresenter @Inject constructor(): BasePresenter<ForgetView>(){

    @Inject
    lateinit var userServiceImpl: UserServiceImpl

    fun forget(name:String,vercode:Int){
        if(!checkNetWork()){
            Toast.makeText(context,"当前无网络链接",Toast.LENGTH_SHORT).show()
            return
        }
        mView.showLoading()
        userServiceImpl.fgpwd(name,vercode)
                .execute(object : BaseSubscriber<String>(mView){
                    override fun onNext(t: String) {
                        super.onNext(t)
                        mView.onforget("${t}消息")
                    }
                },lifecycleProvider)
    }


}