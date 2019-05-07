package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.example.baselibrary.common.ActivityManage
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

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


}
