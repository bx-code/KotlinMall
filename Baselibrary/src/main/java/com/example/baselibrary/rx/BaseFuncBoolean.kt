package com.example.baselibrary.rx

import com.example.baselibrary.data.prototal.BaseResp
import rx.Observable
import rx.functions.Func1
import java.util.*

/**
 * Created by baixiao on 2019/4/3.
 */



class BaseFuncBoolean<T>:Func1<BaseResp<T>, Observable<Boolean>>{

    override fun call(t: BaseResp<T>): Observable<Boolean> {
        return if(t.status !=0)
            Observable.just(false)
        else
            Observable.just(true)
    }

}