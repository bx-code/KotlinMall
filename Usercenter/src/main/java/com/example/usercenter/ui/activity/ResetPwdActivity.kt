package com.kotlin.user.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.baselibrary.common.ActivityManage
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ext.enstart
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.activity.LoginActivity
import com.example.usercenter.ui.presenter.ResetPwdPresenter
import com.example.usercenter.ui.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast


/*
    重置密码界面
 */
class ResetPwdActivity : BaseMVPActivity<ResetPwdPresenter>(), ResetPwdView {

    var timelong : Long = 0L

    override fun onResetQueser(json: String) {
        toast("修改成功${json}")
        setResult(2,intentFor<LoginActivity>())
        this.finish()
//        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    override fun initDaggerTwo() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .usermodule(Usermodule()).build().Inject(this)
        mPresenter.mView=this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }


    /*
        初始化视图
     */
    private fun initView() {

        mConfirmBtn.enstart(mPwdEt,{isBtnEnable()})
        mConfirmBtn.enstart(mPwdConfirmEt,{isBtnEnable()})

        mConfirmBtn.OnClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
                return@OnClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }
    }

    /*
        判断按钮是否可用
     */
    private fun isBtnEnable():Boolean{
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
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

//    /*
//        重置密码回调
//     */
//    override fun onResetPwdResult(result: String) {
//        toast(result)
//        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
//    }
}
