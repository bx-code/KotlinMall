package com.kotlin.base.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.example.baselibrary.R

/*
    Glide工具类
    通过 centerCrop()方法 按照原始的长宽比充满全屏和 fitCenter() 方法 对原图的中心区域进行裁剪对图片进行相关设置。YuanFenConverter.kt
 */
object GlideUtils {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).centerCrop().into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).fitCenter().into(imageView)
    }

    /*
        当fragment或者activity失去焦点或者destroyed的时候，Glide会自动停止加载相关资源，确保资源不会被浪费
     */
    fun loadUrlImage(context: Context, url: String, imageView: ImageView){
        Glide.with(context).load(url).placeholder(R.drawable.icon_back).error(R.drawable.icon_back).centerCrop().into(
                object : SimpleTarget<GlideDrawable>() {
                    override fun onResourceReady(resource: GlideDrawable,
                                                 glideAnimation: GlideAnimation<in GlideDrawable>) {
                        imageView.setImageDrawable(resource)
                    }
                })
    }

    fun loadDrawable(context: Context,drawableid : Int,imageView: ImageView){
            Glide.with(context).load(drawableid).into(imageView)
    }
}
