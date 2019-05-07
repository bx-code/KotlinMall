package com.example.usercenter.utils

import com.example.baselibrary.common.Constant
import com.example.provider.common.ProviderConstant
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.user.data.protocol.UserInfo


object UserPrefsUtils{
    /*
            退出登录时，传入null,清空存储
         */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(Constant.KEY_SP_TOKEN,userInfo?.id ?:"")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NAME, userInfo?.userName ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
    }
}