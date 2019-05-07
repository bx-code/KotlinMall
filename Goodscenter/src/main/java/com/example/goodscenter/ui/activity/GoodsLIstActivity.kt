package com.example.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.injection.component.DaggerGoodsComponent
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.presenter.GoodsPresenter
import com.example.goodscenter.presenter.view.GoodsView
import com.example.goodscenter.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.goods.data.protocol.Goods
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.activity_goods.mMultiStateView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class GoodsLIstActivity : BaseMVPActivity<GoodsPresenter>(),GoodsView, BGARefreshLayout.BGARefreshLayoutDelegate{


    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private lateinit var goodsAdapter : GoodsAdapter

    override fun initDaggerTwo() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().Inject(this)
        mPresenter.mView=this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initview()
        initRefreshLayout()
        loadData()
    }


    private fun initview() {
        mGoodsRv.layoutManager = GridLayoutManager(this,2)
        goodsAdapter= GoodsAdapter(this)
        mGoodsRv.adapter = goodsAdapter
        goodsAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })
    }

    fun loadData(){
//        mMultiStateView.startLoading()
        mMultiStateView.startLoading()
        if(intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,0)==0){
            mPresenter.GetGoodLists(intent.getIntExtra("parentId",1),mCurrentPage)
        }else{
            mPresenter.GetGoodListsByKeyWord(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD),1)
        }

    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this,true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    override fun onGoods(data: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if(data!=null && data.size>0){
            mMaxPage = data[0].maxPage
            if(mCurrentPage ==1){
                goodsAdapter.setData(data)
            }else{
                goodsAdapter.dataList.addAll(data)
                goodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if(mCurrentPage<mMaxPage){
            mCurrentPage++
            loadData()
            true
        }else{
            toast("已经是最后一页")
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
            mCurrentPage=1
            loadData()
    }

}