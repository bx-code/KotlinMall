package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.goodscenter.data.repository.CartRepository
import com.example.goodscenter.service.CartService
import rx.Observable
import javax.inject.Inject


/**
 * 封装数据层
 */
class CartServiceImpl @Inject constructor(): CartService {

    @Inject
    lateinit var cart : CartRepository

    override fun toCart() {
    }

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
            return  cart.addCartDate(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku).convert()
    }
}