package com.example.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.baselibrary.R
import com.example.baselibrary.R.id.mRightTv
import com.example.baselibrary.ext.OnClick
import org.w3c.dom.Text

/**
 * Created by baixiao on 2019/4/4.
 *
 * 自定义 HeaderBar 并添加属性
 */

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

      private var isshowback =true
      private var titletext : String? = null
      private var righttext : String? = null

      lateinit var righttv:TextView

      lateinit var imageBack:ImageView

      lateinit var titletv : TextView

      init {
              val typedArray = context.obtainStyledAttributes(attrs,R.styleable.HeaderBar)
              isshowback = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,false)
              titletext = typedArray.getString(R.styleable.HeaderBar_titleText)
              righttext = typedArray.getString(R.styleable.HeaderBar_rightText)
              initView()
      }

      private fun initView() {
            val  view :View=  View.inflate(context,R.layout.layout_header_bar,this)
                 imageBack = view.findViewById<ImageView>(R.id.mLeftIv)
                 titletv=view.findViewById<TextView>(R.id.mTitleTv)
                 righttv=view.findViewById<TextView>(R.id.mRightTv)
            imageBack.visibility= if(isshowback) View.VISIBLE else View.GONE

            titletext?.let {
                  titletv.text=it
            }

            righttext?.let {
                  righttv.visibility=View.VISIBLE
                  righttv.text=it
            }

            imageBack.OnClick {
                if(context is Activity){
                    (context as Activity).finish()
                }
            }
      }

    fun  getRightView():TextView{
        return righttv
    }
}