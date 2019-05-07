package com.example.baixiao.twokotlinmall

import android.content.Context
import javax.inject.Inject

/**
 * Created by baixiao on 2019/4/2.
 */

class FirstClass @Inject constructor() : HiFirst{
    @Inject
    lateinit var scoundclass : ScoundClass

    override fun hi(code: Int,context: Context) {
        when(code){
            1 ->  scoundclass.sayHi("啦啦啦",context)
            2 ->  scoundclass.sayHi("哈哈哈",context)
        }
    }

}