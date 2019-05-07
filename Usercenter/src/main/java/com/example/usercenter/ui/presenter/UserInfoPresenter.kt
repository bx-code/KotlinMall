package com.example.usercenter.ui.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.usercenter.service.impl.UploadServiceImpl
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.ui.presenter.view.UserInfoView
import com.kotlin.user.data.protocol.UserInfo
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>(){

    @Inject
    lateinit var userserviceImpl : UserServiceImpl
    @Inject
    lateinit var uploadImpl : UploadServiceImpl

    fun Upload(){
        uploadImpl.getUploadToken().execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                super.onNext(t)
                mView.upload(t)
            }

        },lifecycleProvider)
    }

   fun saveUserInfo(serName: String, userGender: String, userSign: String){
       userserviceImpl.saveUserInfo(serName, userGender, userSign).execute(object : BaseSubscriber<UserInfo>(mView){
           override fun onNext(t: UserInfo) {
               super.onNext(t)
               mView.loadUserInfo(t)
           }
       },lifecycleProvider)
   }
}