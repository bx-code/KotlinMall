package com.example.goodscenter.service

import com.example.baselibrary.data.prototal.BaseResp
import rx.Observable

interface CartService {

    fun toCart()

    //添加到购物车
    fun addCart(goodsId: Int,goodsDesc: String,goodsIcon: String,goodsPrice: Long,goodsCount: Int,goodsSku: String):Observable<Int>
}