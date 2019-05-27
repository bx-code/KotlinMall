package com.example.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.fragment.BaseMVPFragement
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.event.AddCartEvent
import com.example.goodscenter.event.GoodsDetailImageEvent
import com.example.goodscenter.event.SkuChangedEvent
import com.example.goodscenter.injection.component.DaggerGoodsComponent
import com.example.goodscenter.injection.module.CartModule
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.presenter.GoodsDetailPresenter
import com.example.goodscenter.presenter.view.GoodsDetailView
import com.example.goodscenter.ui.activity.GoodsDetailActivity
import com.example.goodscenter.widgets.GoodsSkuPopView
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.data.protocol.Goods
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.toast

class GoodsDetailTabOneFragment  : BaseMVPFragement<GoodsDetailPresenter>(),GoodsDetailView{

    private var mCurGoods:Goods? = null
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation


    //dagger 注入
    override fun initDaggerTwo() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).cartModule(CartModule()).build().Inject(this)
        mPresenter.mView=this
    }

    private lateinit var mSkuPop: GoodsSkuPopView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          super.onCreateView(inflater, container, savedInstanceState)
          return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPop()
        initView()
        initObserve()
        initAnim()
        mPresenter.onGoodsDetail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun initObserve() {
        Bus.observe<AddCartEvent>()
                .subscribe{
                    AddCart()
                }.registerInBus(this)

        Bus.observe<SkuChangedEvent>()
                .subscribe{
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() +GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount()+"件"
                }.registerInBus(this)

    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置 (当banner模式中有指示器时)
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mSkuView.OnClick {
            mSkuPop.showAsDropDown((activity as GoodsDetailActivity).getRootView(),Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,0,0)
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }


    private fun initPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
        mSkuPop.setOnDismissListener{
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    //商品详情接口
    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }


    /*
       加载SKU数据
    */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    private fun AddCart(){
        mCurGoods!!.let {
            mPresenter.addCart(it.id,it.goodsDesc,it.goodsDefaultIcon, it.goodsDefaultPrice,  mSkuPop.getSelectCount(),mSkuPop.getSelectSku())
        }
    }

    //添加购物车接口
    override fun onAddCartResult(result: Int) {
        activity!!.toast("添加了${result}次")
    }
    /*
    初始化缩放动画
    */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

}
