package com.example.baixiao.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.baixiao.adpter.HomeDiscountAdapter
import com.example.baixiao.twokotlinmall.R
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.ui.activity.SearchGoodsActivity
import com.example.messagecenter.inter.OnChickFlipperLisener
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.mFlipperView
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.startActivity


class HomeFragment : BaseFragment(){

//    private  lateinit var mHomeBanner :Banner
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        NewFlipperView()
        SetViewAdapter()
        OnTopic()
    }

    private fun initView() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.DepthPage)
        mHomeBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER)
        mHomeBanner.setOnBannerListener(object : OnBannerListener{
            override fun OnBannerClick(position: Int) {
                Toast.makeText(activity,"one${position}",Toast.LENGTH_LONG).show()
            }
        })
        mSearchEt.setOnClickListener {
            activity?.startActivity<SearchGoodsActivity>()
        }
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
    }

    private fun NewFlipperView(){
        mFlipperView.setData(arrayOf("通知——","通知二"))
        mFlipperView.setSubscript(object : OnChickFlipperLisener{
            override fun OnChick(count: Int) {
                   Toast.makeText(activity,"通知${count}",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun SetViewAdapter(){
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mRecycleView.layoutManager = manager

        val discountAdapter  = HomeDiscountAdapter(activity!!)
        mRecycleView.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
        discountAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<String>{
            override fun onItemClick(item: String, position: Int) {
                Toast.makeText(activity,"one${item}",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun OnTopic(){
        //话题
        mTopicPager.adapter = TopicAdapter(activity!!, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }
}