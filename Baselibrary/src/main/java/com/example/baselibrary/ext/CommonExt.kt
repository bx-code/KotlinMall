package com.example.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.baselibrary.R
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.data.prototal.BaseResp
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.baselibrary.rx.BaseSubscriber
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.GlideUtils
import com.kotlin.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by baixiao on 2019/3/30.
 *
 * 使用RxKotlin 扩展方法 execute 链式调用 Observable 并调用 compose 绑定 Rxlifecycler 防止当使用RxAndroid订阅并执行耗时任务后，当Activity被finish时，
 * 如果耗时任务还未完成，没有及时取消订阅，就会导致Activity无法被回收，从而引发内存泄漏。
 * BaseSubscriber<T> : 自定义订阅者
 * LifecycleProvider<*> : lifecycle对象
 */

fun <T> Observable<T>.execute(subscriber:BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
        this.subscribeOn(rx.schedulers.Schedulers.io())
                .compose(lifecycleProvider.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
}

fun View.OnClick(method: ()->Unit) {
       this.setOnClickListener { method() }
}

fun View.OnClick(listener:View.OnClickListener){
     this.setOnClickListener { listener }
}

fun String.isCheck():Int{
    if(this==null || this.isEmpty()){
        return 0
    }else{
        try {
            return this.toInt()
        }catch (e:Exception){
            return 3
        }

    }
}

fun Button.enstart(et :EditText,method: () -> Boolean){
      val but=this
      et.addTextChangedListener(object : DefaultTextWatcher(){
          override fun afterTextChanged(s: Editable?) {
              super.afterTextChanged(s)
              but.isEnabled=method()
          }
      })
}

fun ImageView.onLoadUrl(url : String){
        GlideUtils.loadImage(BaseApplication.context,url,this)
}
/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}


/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

/*
 * List 为空判断
 */
fun <T> MutableList<T>.isMutabNotNull():Boolean{
        if(this!=null && this.size>0)
            return true
        else
            return false

}

//fun  <T> Observable<BaseResp<T>>.convert() : Observable<T>{
//     return this.flatMap { BaseFunc()}
//}

//fun <T> Observable<BaseResp<T>>.convert_boolean():Observable<Boolean>{
//     return this.flatMap { BaseFuncBoolean() }
//}

