package com.example.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.baselibrary.common.ActivityManage
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ext.enstart
import com.example.baselibrary.ext.isCheck
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.presenter.ForgetPresenter
import com.example.usercenter.ui.presenter.LoginPresenter
import com.example.usercenter.ui.presenter.view.ForgetView
import com.example.usercenter.ui.presenter.view.LoginView
import com.kotlin.user.ui.activity.ResetPwdActivity
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.mHeaderBar
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 * Created by baixiao on 2019/3/30.
 * 忘记密码
 */

class ForgetActivity: BaseMVPActivity<ForgetPresenter>(), ForgetView,View.OnClickListener {


    var timelong : Long =0

    lateinit var name:String
    var codedEt=0


    //调用实例化后的dagger2对象
    override fun initDaggerTwo() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .usermodule(Usermodule()).build().Inject(this)
        mPresenter.mView=this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        mNextBtn.setOnClickListener(this)

        mNextBtn.enstart(mMobileEt,{isenkong()})
        mNextBtn.enstart(mVerifyCodeEt,{isenkong()})

    }

    override fun onPostResume() {
        super.onPostResume()
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.mNextBtn ->{
                name = mMobileEt.text.toString()
                codedEt = mVerifyCodeEt.text.toString().isCheck()
                mPresenter.forget(name,codedEt)

            }
        }
    }

    private fun isenkong(): Boolean{
        return  mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onforget(verstring: String) {
        toast(verstring)
//        startActivity<ResetPwdActivity>("mobile" to name)
        startActivityForResult<ResetPwdActivity>(2,"mobile" to name)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==2){
            this.finish()
        }
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if(time - timelong >2000){
            toast("双击退出app")
            timelong=time
        }else{
            ActivityManage.instance.appExit(this)
        }

    }
}