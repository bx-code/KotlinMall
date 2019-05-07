package com.example.baixiao.ui

import android.os.Bundle
import com.example.baixiao.twokotlinmall.R
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.usercenter.utils.UserPrefsUtils
import com.kotlin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mLogoutBtn.OnClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }


}