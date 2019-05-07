package com.example.goodscenter.service

import com.kotlin.goods.data.protocol.Category
import rx.Observable


interface CategoryService {
    //MutableList<Category>?
    fun intoCateGory(portid : Int) : Observable<MutableList<Category>?>
}