package com.example.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.example.baselibrary.R
import org.jetbrains.anko.find

/**
 * Created by baixiao on 2019/4/8.
 */

class ProgressLoading private constructor(context : Context,theme : Int) : Dialog(context,theme){



    companion object {
        private lateinit var mProgress :ProgressLoading
        var animload : AnimationDrawable?=null
        fun create(context: Context):ProgressLoading{
            mProgress= ProgressLoading(context,R.style.LightProgressDialog)
            mProgress.setContentView(R.layout.progress_dialog)
            mProgress.setCancelable(true)
            mProgress.window.attributes.gravity=Gravity.CENTER
            mProgress.setCanceledOnTouchOutside(false)

            val lp = mProgress.window.attributes
            lp.dimAmount = 0.2f
            mProgress.window.attributes = lp

//            animload = mProgress.findViewById<ImageView>(R.id.iv_loading) as AnimationDrawable
             val loadimg = mProgress.find<ImageView>(R.id.iv_loading)
             animload = loadimg.background as AnimationDrawable
             return mProgress
        }
    }

    fun showLoading(){
        super.show()
        animload?.start()
    }

    fun hideLoading(){
        super.dismiss()
        animload?.stop()
    }

}