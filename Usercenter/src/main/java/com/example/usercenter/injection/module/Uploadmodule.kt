package com.example.usercenter.injection.module

import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UploadServiceImpl
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by baixiao on 2019/4/2.
 */

@Module
class Uploadmodule{
    @Provides
    fun providesService(service : UploadServiceImpl):UploadService{
        return service
    }
}