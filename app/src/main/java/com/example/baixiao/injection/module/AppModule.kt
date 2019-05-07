package com.example.baixiao.injection.module

import com.example.baixiao.twokotlinmall.FirstClass
import com.example.baixiao.twokotlinmall.HiFirst
import dagger.Module
import dagger.Provides

/**
 * Created by baixiao on 2019/4/2.
 * dagger2 对 接口 没有构造函数的时候 要使用Module 来和Component链接
 */

@Module
class AppModule{
    @Provides
    fun proviadApp(firstclass: FirstClass):HiFirst{
                return firstclass
    }
}