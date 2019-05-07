package com.example.usercenter.ui.presenter.view

import com.example.baselibrary.ui.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

interface UserInfoView : BaseView {

    fun upload(reselt : String)

    fun loadUserInfo(userInfo : UserInfo)

}