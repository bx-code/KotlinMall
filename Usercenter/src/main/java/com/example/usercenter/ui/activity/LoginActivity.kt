package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.common.ActivityManage
import com.example.baselibrary.common.Constant
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ext.enstart
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.provider.router.RouterPath
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.presenter.LoginPresenter
import com.example.usercenter.ui.presenter.view.LoginView
import com.example.usercenter.utils.UserPrefsUtils
import com.kotlin.user.data.protocol.UserInfo
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by baixiao on 2019/3/30.
 */

@Route(path = RouterPath.UserCenter.path_login)
class LoginActivity: BaseMVPActivity<LoginPresenter>(), LoginView,View.OnClickListener {



    var timelong : Long =0


    //调用实例化后的dagger2对象
    override fun initDaggerTwo() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .usermodule(Usermodule()).build().Inject(this)
        mPresenter.mView=this
    }

    lateinit var name:String
    lateinit var iphoen:String


    //刷新线程的方法
    override fun ondata(json: String) {
        toast("登陆成功${json}")
        var i=1
        UserPrefsUtils.putUserInfo( UserInfo(i.toString(),Constant.HEAD_IMAGEURL,name,"1",iphoen,"day by day"))
//        startActivity<UserInfoActivity>()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mLoginBtn.setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)
        mHeaderBar.getRightView().OnClick {
             startActivity<RegisterActivity>()
        }
        mLoginBtn.enstart(mIphone,{isenkong()})
        mLoginBtn.enstart(mUsernemEt,{isenkong()})

    }

    override fun onPostResume() {
        super.onPostResume()
    }

    //回退按键
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if(time-timelong >2000){
            toast("再按一次退出app")
            timelong=time
        }else{
            ActivityManage.instance.appExit(this)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mLoginBtn ->{
                name = mUsernemEt.text.toString()
                iphoen = mIphone.text.toString()
                mPresenter.login(name,iphoen)
            }
            R.id.mForgetPwdTv ->{
                startActivity<ForgetActivity>()
            }
        }
    }

    private fun isenkong(): Boolean{
        return  mUsernemEt.text.isNullOrEmpty().not() &&
                mIphone.text.isNullOrEmpty().not()

    }
}