package com.example.usercenter.data.repository

import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.net.RetrofitFactory
import com.example.usercenter.data.api.UploadApi
import com.example.usercenter.data.api.UserApi
import com.example.usercenter.data.porotocol.ForgetReq
import com.example.usercenter.data.porotocol.LoginReq
import com.example.usercenter.data.porotocol.RegisterReq
import com.example.usercenter.data.porotocol.ResetReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

class UploadRepository @Inject constructor() {

    fun Upload():Observable<BaseResp<String>>{
         return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }

}