package com.example.goodscenter.data.repository

import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.net.RetrofitFactory
import com.example.goodscenter.data.api.GoodsApi
import com.example.goodscenter.data.protocal.GetGoodsKeyWord
import com.example.goodscenter.data.protocal.GetGoodsListByKeywordReq
import com.kotlin.goods.data.protocol.GetGoodsListReq
import com.kotlin.goods.data.protocol.Goods
import rx.Observable
import javax.inject.Inject


class GoodsRepository @Inject constructor(){

    fun GoodList(categoryId: Int,pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
       return  RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }

    fun GoodListKeyword(KeyWord: String,pageNo: Int): Observable<BaseResp<MutableList<Goods>?>> {
        return  RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsListByKeyword(GetGoodsListByKeywordReq(KeyWord, pageNo))
    }
}