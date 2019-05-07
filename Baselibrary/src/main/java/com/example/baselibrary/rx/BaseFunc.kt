package com.example.baselibrary.rx

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.baselibrary.data.prototal.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by baixiao on 2019/4/3.
 */



class BaseFunc<T> :Func1<BaseResp<T>,Observable<T>> {

    override fun call(t: BaseResp<T>): Observable<T> {
        if(t.status !=0){
            return Observable.error(BaseException(t.status,t.message))
        }
            return Observable.just(t.data)

    }

}


class BaseFuncStr<T> :Func1<BaseResp<T>,Observable<String>> {

    override fun call(t: BaseResp<T>): Observable<String> {
        if(t.status !=0){
            return Observable.error(BaseException(t.status.toInt(),t.message))
        }
        return Observable.just(t.message)

    }

}