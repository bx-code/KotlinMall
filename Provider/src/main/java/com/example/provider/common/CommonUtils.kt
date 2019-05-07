package com.example.provider.common

import com.example.baselibrary.common.Constant
import com.kotlin.base.utils.AppPrefsUtils


/*
    顶级函数，判断是否登录
 */
fun isLogined():Boolean{
    return AppPrefsUtils.getString(Constant.KEY_SP_TOKEN).isNotEmpty()
}