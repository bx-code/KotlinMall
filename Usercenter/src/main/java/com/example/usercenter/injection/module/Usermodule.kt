package com.example.usercenter.injection.module

import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by baixiao on 2019/4/2.
 */

@Module
class Usermodule{
    @Provides
    fun providesService(service : UserServiceImpl):UserService{
        return service
    }
}