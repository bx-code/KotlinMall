package com.example.baselibrary.net

import com.example.baselibrary.common.Constant
import com.kotlin.base.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by baixiao on 2019/3/30.
 */


class RetrofitFactory internal constructor(){

    /**
     * 单例模式 by lazy 延迟加载 初始化
     */
    companion object {
        val instance : RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit : Retrofit
    private val interceptor : Interceptor


    //初始化方法
    init {
        interceptor = Interceptor {
            chainaa ->
            val request = chainaa.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("charset","UTF-8")
//                    .addHeader("token", AppPrefsUtils.getString(Constant.KEY_SP_TOKEN))
                    .build()
            chainaa.proceed(request)
        }
        //创建 Retrofit 对象
        retrofit = Retrofit.Builder()
                .baseUrl(Constant.SERVER_ADDRESS) //服务器地址
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()

    }

    private fun initClient() : OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS) //链接超时时间
                .readTimeout(30,TimeUnit.SECONDS)  //读取超时时间
                .build()
    }

    //日志输出 拦截器
    private fun initLogInterceptor() : Interceptor{
        val interceptor =   HttpLoggingInterceptor()
        interceptor.level =HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    //主体调用方法 暴露给外层调用创建 Retrofit
    fun <T> create (service : Class<T>):T{
        return retrofit.create(service)
    }

}