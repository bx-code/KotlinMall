package com.example.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMVPFragement
import com.example.goodscenter.R
import com.example.goodscenter.injection.component.DaggerCatGoryComponent
import com.example.goodscenter.injection.module.CatGoryModule
import com.example.goodscenter.presenter.CatGoryPresenter
import com.example.goodscenter.presenter.view.CatGoryView
import com.example.goodscenter.ui.activity.GoodsLIstActivity
import com.example.goodscenter.ui.adapter.SecondCategoryAdapter
import com.example.goodscenter.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.goods.data.protocol.Category
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.startActivity

class CategoryFramgment : BaseMVPFragement<CatGoryPresenter>(), CatGoryView {


    private lateinit var cateAdapter : TopCategoryAdapter

    private lateinit var secondAdapter: SecondCategoryAdapter

    override fun initDaggerTwo() {
        DaggerCatGoryComponent.builder().activityComponent(activityComponent).catGoryModule(CatGoryModule()).build().Inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        CloseId()
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        cateAdapter = TopCategoryAdapter(activity!!)
        mTopCategoryRv.adapter = cateAdapter
        cateAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {
                for(category in cateAdapter.dataList){
                    category.isSelected = item.id == category.id
                }
                cateAdapter.notifyDataSetChanged()
                CloseId(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context,3)
        secondAdapter = SecondCategoryAdapter(activity!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category>{
            override fun onItemClick(item: Category, position: Int) {
                    activity!!.startActivity<GoodsLIstActivity>("parentId" to item.parentId)

            }
        })
    }

    private fun CloseId(id : Int = 0){
        if(id!=0){
            mMultiStateView.startLoading()
        }
        mPresenter.toCatGoryInfo(id)
    }

    override fun toData(date: MutableList<Category>?) {
        if(date!=null && date.size>0){
            if(date[0].parentId==0){
                date[0].isSelected=true
                cateAdapter.setData(date)
                mPresenter.toCatGoryInfo(date[0].id)
            }else{
                secondAdapter.setData(date)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        }else{
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun ErrorData(errorinfo: Throwable) {
         Log.e("Error_info",errorinfo.message)
         errorinfo.printStackTrace()
    }
}