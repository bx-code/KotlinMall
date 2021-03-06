package com.example.baixiao.adpter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baixiao.twokotlinmall.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * 折扣商品展示 adpater
 */

class HomeDiscountAdapter(context : Context) : BaseRecyclerViewAdapter<String,HomeDiscountAdapter.ViewHolder>(context){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view= LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item,parent,false)
         return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadImage(mContext,dataList[position],holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "¥123.2"
        holder.itemView.mDiscountBeforeTv.text="$1000"
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        init {
            //设置TextView样式 添加中划线
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}