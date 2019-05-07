package com.example.usercenter.data.repository

import com.example.baselibrary.common.Constant
import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.net.RetrofitFactory
import com.example.usercenter.data.api.UserApi
import com.example.usercenter.data.porotocol.ForgetReq
import com.example.usercenter.data.porotocol.LoginReq
import com.example.usercenter.data.porotocol.RegisterReq
import com.example.usercenter.data.porotocol.ResetReq
import com.kotlin.user.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

class UserRepository @Inject constructor() {

    fun register_post(locast : String,password : String,code : String):Observable<BaseResp<String>>{
         return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(locast,password,code))
    }

    fun login(username :String,iphone: String):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).login_post(LoginReq(username, iphone))
    }

    fun fgpwd(iphone: String,ifvidecode :Int):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).fgpwd(ForgetReq(iphone, ifvidecode))
    }

    fun resetPwd(mobile : String,mpwd : String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java).fgresetpwd(ResetReq(mobile,mpwd))
    }

    fun saveUserInfo(userName: String, userGender: String, userSign: String):Observable<BaseResp<UserInfo>>{
        return Observable.just(BaseResp(0,"保存成功",UserInfo("101", Constant.HEAD_IMAGEURL,userName,userGender,"1878273873",userSign)))
    }

}