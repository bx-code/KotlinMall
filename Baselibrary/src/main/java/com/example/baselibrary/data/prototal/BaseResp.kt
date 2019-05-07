package com.example.baselibrary.data.prototal

/**
 * Created by baixiao on 2019/3/30.
 */

 class BaseResp<out T>(val status : Int,val message : String,val data : T)