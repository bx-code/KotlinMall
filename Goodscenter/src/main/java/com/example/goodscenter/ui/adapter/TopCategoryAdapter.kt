package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.baselibrary.common.BaseApplication.Companion.context
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

class TopCategoryAdapter(context : Context) : BaseRecyclerViewAdapter<Category,TopCategoryAdapter.ViewHolder>(context) {

    private lateinit var Nametv : TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.layout_top_category_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val  items =dataList[position]
        holder.itemView.mTopCategoryNameTv.text=items.categoryName
        holder.itemView.mTopCategoryNameTv.isSelected = items.isSelected

    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
}