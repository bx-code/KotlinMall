package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import com.example.goodscenter.ui.fragment.GoodsDetailTabTwoFragment

class GoodsDetailVpAdapter(fm : FragmentManager,context : Context) : FragmentPagerAdapter(fm){

    private val mTitle = arrayOf("商品","详情")

    override fun getItem(position: Int): Fragment {
         return if (position == 0){
             GoodsDetailTabOneFragment()
         }else{
             GoodsDetailTabTwoFragment()
         }
    }

    override fun getCount(): Int {
        return mTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  mTitle[position]
    }
}