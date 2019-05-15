package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.goodscenter.data.repository.GoodsRepository
import com.example.goodscenter.service.GoodsService
import com.kotlin.goods.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

class GoodsServiceImpl @Inject constructor(): GoodsService{



    @Inject
    lateinit var goodsRepository: GoodsRepository
    //商品列表
    override fun toGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
                return goodsRepository.GoodList(categoryId,pageNo).convert()
    }
    //搜索
    override fun GoodsKeyWord(keyWord: String, pageNo: Int): Observable<MutableList<Goods>?> {
                return goodsRepository.GoodListKeyword(keyWord,pageNo).convert()
    }
    //商品详情
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
                return goodsRepository.GoodDetail(goodsId).convert()
    }

}
