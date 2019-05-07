package com.example.usercenter.data.api

import com.example.baselibrary.data.prototal.BaseResp
import com.example.usercenter.data.porotocol.ForgetReq
import com.example.usercenter.data.porotocol.LoginReq
import com.example.usercenter.data.porotocol.RegisterReq
import com.example.usercenter.data.porotocol.ResetReq
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * Created by baixiao on 2019/3/30.
 */


interface UserApi {

        /*
       用户注册
    */
        @POST("userCenter/register")
        fun register(@Body req:RegisterReq):Observable<BaseResp<String>>

        @GET("/telematics/v3/weather?location=北京")
        fun register_get():Observable<BaseResp<String>>

        @POST("/telematics/v3/weather?location=北京")
        fun register_post(@Body rep :RegisterReq):Observable<BaseResp<String>>


        @POST("/telematics/v3/weather?location=北京")
        fun login_post(@Body rep: LoginReq):Observable<BaseResp<String>>


        @POST("/telematics/v3/weather?location=北京")
        fun fgpwd(@Body rep: ForgetReq):Observable<BaseResp<String>>

        @POST("/telematics/v3/weather?location=北京")
        fun fgresetpwd(@Body rep: ResetReq):Observable<BaseResp<String>>
}