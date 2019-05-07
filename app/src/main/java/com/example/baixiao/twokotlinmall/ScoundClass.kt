package com.example.baixiao.twokotlinmall

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

/**
 * Created by baixiao on 2019/4/2.
 */

class ScoundClass @Inject constructor(){

    fun sayHi(name : String,context: Context){
        println("Hi,$name")
        Toast.makeText(context,"Hi,$name",Toast.LENGTH_SHORT).show()
    }
}