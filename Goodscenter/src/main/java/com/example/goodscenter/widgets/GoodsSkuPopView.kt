package com.example.goodscenter.widgets

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import com.example.goodscenter.R
import android.view.ViewGroup.LayoutParams
import android.widget.RelativeLayout
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ext.onLoadUrl
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.data.protocal.GoodsSku
import com.example.goodscenter.event.AddCartEvent
import com.example.goodscenter.getEditText
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.DefaultTextWatcher
import kotlinx.android.synthetic.main.layout_sku_pop.view.*

/*
    商品SKU弹层
 */
class GoodsSkuPopView(context: Activity) : PopupWindow(context), View.OnClickListener {
    //根视图
    private val mRootView : View
    private val mContext : Context
    private val mSkuViewList = arrayListOf<SkuView>()

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
            override fun onTouch(p0: View?, p1: MotionEvent): Boolean {
                 val height = mRootView.findViewById<RelativeLayout>(R.id.mPopView).top
                 mRootView.findViewById<View>(R.id.mPopView).top
                 val y = p1.y.toInt()
                 if(p1.action == MotionEvent.ACTION_UP){
                        if(y< height){
                            dismiss()
                        }
                 }
                 return true
            }

        })

    }

    private fun initView() {
        mRootView.mCloseIv.setOnClickListener(this)
        mRootView.mAddCartBtn.setOnClickListener(this)

        mRootView.mSkuCountBtn.setCurrentNumber(1)
        mRootView.mSkuCountBtn.getEditText().addTextChangedListener(
                object : DefaultTextWatcher(){
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                        Bus.send(SkuChangedEvent())
                    }
                }
        )
        mRootView.mAddCartBtn.setOnClickListener {
            Bus.send(AddCartEvent())
            dismiss()
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mCloseIv -> dismiss()
            R.id.mAddCartBtn -> {
                dismiss()
            }
        }
    }

    /*
       设置商品图标
    */
    fun setGoodsIcon(text: String) {
        mRootView.mGoodsIconIv.onLoadUrl(text)
    }

    /*
        设置商品价格
     */
    fun setGoodsPrice(text: Long) {
        mRootView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(text)
    }

    /*
        设置商品编号
     */
    fun setGoodsCode(text: String) {
        mRootView.mGoodsCodeTv.text = "商品编号:" + text
    }

    /*
     设置商品SKU
     */
    fun setSkuData(list: List<GoodsSku>) {
        for (goodSku in list) {
            val skuView = SkuView(mContext)
            skuView.setSkuView(goodSku)
            mSkuViewList.add(skuView)
            mRootView.mSkuView.addView(skuView)
        }
    }

    /*u
        获取选中的SKU
     */
    fun getSelectSku(): String {
        var skuInfo = ""
        for (skuView in mSkuViewList) {
            skuInfo += skuView.getSkuInfo().split(GoodsConstant.SKU_SEPARATOR)[1] + GoodsConstant.SKU_SEPARATOR
        }
        return skuInfo.take(skuInfo.length - 1)//刪除最后一个分隔
    }

    /*
        获取商品数量
     */
    fun getSelectCount() = mRootView.mSkuCountBtn.number


}
