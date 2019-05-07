package com.example.goodscenter.service

import com.example.baselibrary.data.prototal.BaseResp
import com.kotlin.goods.data.protocol.Goods
import rx.Observable

interface GoodsService {

    fun toGoodsList(categoryId: Int,pageNo: Int): Observable<MutableList<Goods>?>

    fun GoodsKeyWord(keyWord: String,pageNo: Int): Observable<MutableList<Goods>?>
}