package com.example.usercenter.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable

/**
 * Created by baixiao on 2019/3/30.
 */

interface  UserService{

    fun register(iphone : String,password: String,code : String):Observable<String>

    fun register_get(name : String):Observable<String>

    fun Login(user:String,iphone:String):Observable<String>

    fun fgpwd(iphone: String,version:Int):Observable<String>

    fun resetpwd(mobile:String,password: String):Observable<String>

    fun saveUserInfo(userName:String,userGender:String,userSign:String):Observable<UserInfo>
}
