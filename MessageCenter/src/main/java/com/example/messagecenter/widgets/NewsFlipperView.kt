package com.kotlin.message.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ViewFlipper
import android.widget.TextView
import com.example.messagecenter.R
import com.example.messagecenter.inter.OnChickFlipperLisener
import org.jetbrains.anko.*

/*
    公告组件封装
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private val mFlipperView: ViewFlipper
    private lateinit var OnListener : OnChickFlipperLisener
    private var i = 0
    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = rootView.find(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out)

        addView(rootView)
    }


    /*
        构建公告
     */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        textView.layout(10,10,10,10)
        return textView
    }

    /*
        设置公告数据
     */
    fun setData(data: Array<String>) {

        for (text in data) {
            val view : View = buildNewsView(text)
            mFlipperView.addView(view)
            view.setOnClickListener {
                OnListener.OnChick(i)
                i++
            }
        }
        mFlipperView.startFlipping()
    }

    /**
     * 设置点击下标
     */
    fun setSubscript(OnListener : OnChickFlipperLisener){
        this.OnListener = OnListener
    }

}
