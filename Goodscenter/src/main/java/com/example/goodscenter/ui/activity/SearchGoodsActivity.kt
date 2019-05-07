package com.example.goodscenter.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.baselibrary.common.Constant
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.ui.adapter.SearchHistoryAdapter
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import com.example.goodscenter.ui.activity.GoodsLIstActivity as GoodsLIstActivity

class SearchGoodsActivity : BaseActivity(),View.OnClickListener{

    lateinit var adapter : SearchHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)
        initview()
    }

    fun initview(){
        mLeftIv.setOnClickListener(this)
        mSearchTv.setOnClickListener(this)
        mSearchHistoryRv.setOnClickListener(this)
        mSearchHistoryRv.layoutManager = LinearLayoutManager(this)
        adapter = SearchHistoryAdapter(this)
        mSearchHistoryRv.adapter = adapter
        //item点击事件
        adapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
            override fun onItemClick(item: String, position: Int) {
                enterGoodsList(item)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    
    override fun onClick(view: View) {
        when(view.id){
            R.id.mLeftIv -> finish()
            //搜索
            R.id.mSearchTv -> {
                onSearch()
            }
            //清空历史
            R.id.mClearBtn ->{
                AppPrefsUtils.remove(Constant.SET_SEARCH)
                loadData()
            }
        }
    }

    private fun loadData() {
        val set = AppPrefsUtils.getStringSet(Constant.SET_SEARCH)
        mNoDataTv.setVisible(set.isEmpty())
        mDataView.setVisible(set.isNotEmpty())
        if (set.isNotEmpty()) {
            val list = mutableListOf<String>()
            list.addAll(set)
            adapter.setData(list)
        }
    }

    private fun onSearch(){
        if(mSearchTv.text.isNullOrEmpty()){
            toast("请输入搜索关键字")
        }else{
            val inputValue = mKeywordEt.text.toString()
            AppPrefsUtils.putStringSet(Constant.SET_SEARCH,mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }


    private fun enterGoodsList(item: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsLIstActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to item
        )
    }

}