package com.example.baselibrary.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.baselibrary.R
import com.example.baselibrary.common.ActivityManage
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

/**
 * Created by baixiao on 2019/3/30.
 * 项目中activity基类 继承 Rxlifecycle 绑定生命周期
 */

open class BaseActivity : RxAppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建activity 添加到actvitiyManage 管理类中
        ActivityManage.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //销毁activity 在activityManage中删除
        ActivityManage.instance.finishActivity(this)
    }

    val contentView : View
         get() {
             val context = find<FrameLayout>(android.R.id.content)
             return context.getChildAt(0)
         }
}
