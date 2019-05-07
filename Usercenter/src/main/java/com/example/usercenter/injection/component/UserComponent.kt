package com.example.usercenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.usercenter.injection.module.Uploadmodule
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.activity.ForgetActivity
import com.example.usercenter.ui.activity.LoginActivity
import com.example.usercenter.ui.activity.RegisterActivity
import com.example.usercenter.ui.activity.UserInfoActivity
import com.kotlin.user.ui.activity.ResetPwdActivity
import dagger.Component
import javax.inject.Inject

/**
 * Created by baixiao on 2019/4/2.
 */

@PerComponentScope
@Component (dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(Usermodule::class,Uploadmodule::class))
interface UserComponent{

    fun Inject(activity:RegisterActivity)
    fun Inject(activity:LoginActivity)
    fun Inject(activity:ForgetActivity)
    fun Inject(activity:ResetPwdActivity)
    fun Inject(activity:UserInfoActivity)

}