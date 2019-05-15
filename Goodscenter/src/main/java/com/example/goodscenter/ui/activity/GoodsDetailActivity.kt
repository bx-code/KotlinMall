package com.example.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.RelativeLayout
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.goodscenter.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initview()
    }

    private fun initview() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager,this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

    }


    fun getRootView(): View {
        return mRootView
    }
}