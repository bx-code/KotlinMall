package com.example.goodscenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.RelativeLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.goodscenter.event.AddCartEvent
import com.example.goodscenter.ui.adapter.GoodsDetailVpAdapter
import com.example.provider.common.afterLogin
import com.example.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity

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
        mAddCartBtn.OnClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }
    }

    fun getRootView(): View {
        return mRootView
    }
}
