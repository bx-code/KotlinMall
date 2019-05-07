package com.example.baselibrary.rx

/**
 * Created by baixiao on 2019/3/30.
 */

class BaseException(val status: Int,val msg : String):Throwable()




class BaseException1  constructor(val status: Int,val msg : String=""):Throwable(){

    init {
       val status=this.status
       val msg=this.msg
    }
}