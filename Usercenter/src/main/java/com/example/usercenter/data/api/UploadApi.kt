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


interface UploadApi {
    /*
        获取七牛云上传凭证
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}