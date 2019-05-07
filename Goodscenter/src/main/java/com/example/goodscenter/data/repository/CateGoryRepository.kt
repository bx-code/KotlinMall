package com.example.goodscenter.data.repository

import com.example.baselibrary.common.Constant
import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.net.RetrofitFactory
import com.example.goodscenter.data.api.CateGoryApi
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject


class CateGoryRepository @Inject constructor(){

    fun toCateGory(projectid : Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CateGoryApi::class.java).getCategory(GetCategoryReq(projectid))
//          return toData(projectid)
    }

    fun toData (projectid : Int): Observable<BaseResp<MutableList<Category>?>>{
        when(projectid){
            0 -> {
                var mutableList : MutableList<Category> =  mutableListOf()
                for(i in 0..12){
                    when(i){
                        0 -> mutableList.add(i, Category(i,"京东一体机",Constant.COMMPAN_URL,i))
                        1 -> mutableList.add(i, Category(i,"游戏笔记本",Constant.COMMPAN_URL,i))
                        2 -> mutableList.add(i, Category(i,"化妆品",Constant.COMMPAN_URL,i))
                        3 -> mutableList.add(i, Category(i,"电脑/电器",Constant.COMMPAN_URL,i))
                        4 -> mutableList.add(i, Category(i,"电脑/电器",Constant.COMMPAN_URL,i))
                        else -> mutableList.add(i, Category(i,"啤酒",Constant.COMMPAN_URL,i))
                    }

                }
                return Observable.just(BaseResp(0,"成功",mutableList))
            }
            1 ->{
                var mutableList : MutableList<Category> =  mutableListOf()
                for(i in 0..10){
                    val category = Category(1,"京东一体机",Constant.COMMPAN_URL,0,true)
                    mutableList.add(i,category)
                }
                return Observable.just(BaseResp(0,"成功",mutableList))
            }
            2 ->{
                var mutableList : MutableList<Category> =  mutableListOf()
                for(i in 0..10){
                    val category = Category(1,"游戏本",Constant.TWOPAN_URL,0,true)
                    mutableList.add(i,category)
                }
                return Observable.just(BaseResp(0,"成功",mutableList))
            }
            3 ->{
                var mutableList : MutableList<Category> =  mutableListOf()
                for(i in 0..10){
                    val category = Category(1,"化妆品",Constant.THREE_uURL,0,true)
                    mutableList.add(i,category)
                }
                return Observable.just(BaseResp(0,"成功",mutableList))
            }
            else ->{
                var mutableList : MutableList<Category> =  mutableListOf()
                for(i in 0..10){
                    val category = Category(1,"啤酒",Constant.FOUR_URL,0,true)
                    mutableList.add(i,category)
                }
                return Observable.just(BaseResp(0,"成功",mutableList))
            }
        }
    }
}