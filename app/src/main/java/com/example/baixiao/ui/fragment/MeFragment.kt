package com.example.baixiao.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.baixiao.twokotlinmall.R
import com.example.baixiao.ui.SettingActivity
import com.example.baselibrary.ext.onLoadUrl
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.provider.common.ProviderConstant
import com.example.provider.common.isLogined
import com.example.usercenter.ui.activity.LoginActivity
import com.example.usercenter.ui.activity.UserInfoActivity
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.GlideUtils
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.startActivity

class MeFragment : BaseFragment(),View.OnClickListener{


    //    private  lateinit var mHomeBanner :Banner
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me,null)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        isUserInfo()
    }

    private fun isUserInfo() {
        if(isLogined()){
            mUserIconIv.onLoadUrl(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }else{
            mUserNameTv.text= resources.getString(R.string.un_login_text)
            Glide.with(context).load(com.example.baselibrary.R.drawable.icon_default_user).into(mUserIconIv)

        }
    }

    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mUserNameTv,R.id.mUserIconIv -> {
                 if(isLogined()){
                     activity!!.startActivity<UserInfoActivity>()
                 }else{
                     activity!!.startActivity<LoginActivity>()
                 }
            }
            R.id.mSettingTv ->{
                activity!!.startActivity<SettingActivity>()
            }
        }
    }


}