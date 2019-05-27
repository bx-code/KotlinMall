package com.example.goodscenter.data.repository

import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.net.RetrofitFactory
import com.example.goodscenter.data.api.CartApi
import com.example.goodscenter.data.protocal.AddCartReq
import rx.Observable
import javax.inject.Inject

/**
 * 购物车对应接口实现
 */
class CartRepository  @Inject constructor(){
    fun addCartDate(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<BaseResp<Int>> {
            return RetrofitFactory.instance.create(CartApi::class.java).addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }
}