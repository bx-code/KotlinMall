package com.example.usercenter.service.impl

import com.example.baselibrary.common.Constant
import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.rx.BaseException
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncStr
import com.example.usercenter.data.repository.UserRepository
import com.example.usercenter.service.UserService
import com.kotlin.user.data.protocol.UserInfo
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
* Created by baixiao on 2019/3/30.
*/

class UserServiceImpl @Inject constructor() :UserService{

    @Inject
    lateinit var userRepository: UserRepository

    override fun register_get(name : String): Observable<String> {
        return userRepository.register_post(name,"","").flatMap(BaseFunc())
    }

    override fun register(iphone : String,password : String,code : String): Observable<String>{
        return userRepository.register_post(iphone,password,code).flatMap(BaseFunc())
    }

    override fun Login(user: String, iphone: String): Observable<String> {
        return userRepository.login(user,iphone).flatMap(BaseFuncStr())
    }

    override fun fgpwd(iphone: String,vercode: Int): Observable<String> {
        return userRepository.fgpwd(iphone,vercode).flatMap(BaseFunc())
    }

    override fun resetpwd(mobile: String, password: String): Observable<String> {
        return userRepository.resetPwd(mobile,password).flatMap (BaseFuncStr())
    }

    override fun saveUserInfo( serName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return userRepository.saveUserInfo(serName,userGender,userSign).flatMap(BaseFunc())
//        Observable.just(UserInfo(1, Constant.HEAD_IMAGEURL,userName,userGender,"",userSign)).flatMap(BaseFunc<UserInfo>());
    }



}


