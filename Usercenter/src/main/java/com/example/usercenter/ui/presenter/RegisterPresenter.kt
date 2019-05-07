package com.example.usercenter.ui.presenter

import android.util.Log
import android.widget.Toast
import com.example.baselibrary.common.Constant
import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.ext.execute
import com.example.baselibrary.rx.BaseException
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.ui.presenter.view.BasePresenter
import com.example.usercenter.data.api.UserApi
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.ui.presenter.view.RegisterVIew
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by baixiao on 2019/3/30.
 */

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterVIew>(){

    val ONE=1
    val TWO=2

    @Inject
    lateinit var userServiceImpl: UserServiceImpl
    //注册的实现方法
    fun register(name : String,password: String,code : Int){
        when(code){
            ONE ->  CommonObservable(name)
            TWO ->  RetrofitObservable()
            else ->{
                MainObservable(name,password)
            }
        }
    }

    fun CommonObservable(name :String){
        Observable.just(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :Subscriber<String>(){
                    override fun onNext(t: String) {
                        mView.ondata(t)
                        Log.e("One_Mview","看到的数据为${t}")
                    }

                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable?) {

                    }

                })
    }

    fun RetrofitObservable(){
        val retrofit:Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.SERVER_ADDRESS)
                .build()

        val mApi = retrofit.create(UserApi::class.java).register_get()

        mApi.flatMap(object : Func1<BaseResp<String>,Observable<String>>{

            override fun call(t: BaseResp<String>): Observable<String> {
                if(t.status !=0){
                    return Observable.error(BaseException(t.status.toInt(),"没有拿到对应的正确参数"))
                }
                return Observable.just(t.message)
            }

        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :Subscriber<String>(){
                    override fun onNext(t: String) {
                        mView.ondata(t)
                        Log.e("Two_Mview","看到的数据为${t}")
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }

    fun MainObservable(iphone : String,password : String,code : String ="123456"){
        if(!checkNetWork()){
            Toast.makeText(context,"当前无网络链接",Toast.LENGTH_SHORT).show()
            return
        }
        mView.showLoading()
        userServiceImpl.register(iphone,password,code)
                .execute(object : BaseSubscriber<String>(mView){
                     override fun onNext(t: String) {
                      super.onNext(t)
                      mView.hideLoading()
                      mView.ondata("看到的数据为${t}")
                      Log.e("Mview","看到的数据为${t}")
                    }

                    override fun onError(e: Throwable?) {
                        super.onError(e)
//                        mView.hideLoading()
                        Log.e("Mview","当前报错${e.toString()}")
                        mView.ondata("返回数据为${e.toString()}")
                    }

                },lifecycleProvider)
    }


}