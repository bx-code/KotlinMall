package com.example.goodscenter.widgets

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import com.example.goodscenter.R
import android.view.ViewGroup.LayoutParams

/*
    商品SKU弹层
 */
class GoodsSkuPopView(context: Activity) : PopupWindow(context), View.OnClickListener {
    //根视图
    private val mRootView : View
    private val mContext : Context

    init {
        mContext = context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mRootView = inflater.inflate(R.layout.layout_sku_pop,null)

        initView()

        // 设置SelectPicPopupWindow的View
        this.contentView = mRootView
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.width = LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.animationStyle = R.style.AnimBottom
        background.alpha = 150
        mRootView.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//                 val height = mRootView.findViewById(R.id.mPopView).top
                 val y = p1?.y!!.toInt()
                 return true
            }

        })


    }

    private fun initView() {

    }


    override fun onClick(p0: View?) {


    }

}