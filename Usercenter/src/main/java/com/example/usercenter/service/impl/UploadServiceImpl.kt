package com.example.usercenter.service.impl

import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.rx.BaseException
import com.example.baselibrary.rx.BaseFunc
import com.example.usercenter.data.repository.UploadRepository
import com.example.usercenter.data.repository.UserRepository
import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
* Created by baixiao on 2019/3/30.
*/

class UploadServiceImpl @Inject constructor() :UploadService{

    @Inject
    lateinit var uploadRepository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return  uploadRepository.Upload().flatMap(BaseFunc<String>())
    }


}