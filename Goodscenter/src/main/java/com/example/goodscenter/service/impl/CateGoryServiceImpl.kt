package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.rx.BaseFunc
import com.example.goodscenter.data.repository.CateGoryRepository
import com.example.goodscenter.service.CategoryService
import com.kotlin.goods.data.protocol.Category
import rx.Observable
import javax.inject.Inject


class CateGoryServiceImpl @Inject constructor() : CategoryService{
    //
    @Inject
    lateinit var cateGory : CateGoryRepository

    override fun intoCateGory(portid: Int) : Observable<MutableList<Category>?>{
        return  cateGory.toCateGory(portid).convert()
//          return  cateGory.toCateGory(portid).flatMap(BaseFunc())
    }
}