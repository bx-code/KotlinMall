package com.example.goodscenter.data.api

import com.example.baselibrary.data.prototal.BaseResp
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface CateGoryApi{
    /*
       获取商品分类列表
    */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}