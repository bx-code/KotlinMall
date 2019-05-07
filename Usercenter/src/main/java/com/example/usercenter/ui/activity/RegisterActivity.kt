package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.example.baselibrary.common.ActivityManage
import com.example.baselibrary.ext.enstart
import com.example.baselibrary.ext.isCheck
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.presenter.RegisterPresenter
import com.example.usercenter.ui.presenter.view.RegisterVIew
import kotlinx.android.synthetic.main.register_layout.*
import org.jetbrains.anko.toast

/**
 * Created by baixiao on 2019/3/30.
 */

class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterVIew,View.OnClickListener {

    var timelong : Long =0


    //调用实例化后的dagger2对象
    override fun initDaggerTwo() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .usermodule(Usermodule()).build().Inject(this)
    }


    lateinit var name:String
    lateinit var pass:String
    var TypeCode=1


    //刷新线程的方法
    override fun ondata(json: String) {
        println("我所看见的为${json}")
        toast("注册成功${json}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        mPresenter.mView=this

        mRegisterBtn.setOnClickListener(this)
        mRegisterBtn.enstart(mMobileEt,{isenkong()})
        mRegisterBtn.enstart(mVerifyCodeEt,{isenkong()})
        mRegisterBtn.enstart(mPwdEt,{isenkong()})
        mRegisterBtn.enstart(mPwdConfirmEt,{isenkong()})

    }

    override fun onPostResume() {
        super.onPostResume()
    }


    fun isenkong(): Boolean{
        return  mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()

    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.mRegisterBtn ->{
                name = mMobileEt.text.toString()
                pass = mPwdEt.text.toString()
                TypeCode=mVerifyCodeEt.text.toString().isCheck()
                if(name.length<11){
                    toast("请输入正确手机号码")
                    return
                }
                if(pass.length<6){
                    toast("密码必须大于6位")
                    return
                }
                if(TypeCode<1000){
                    toast("请输入正确验证码")
//                    return
                }
                mPresenter.register(name,pass,TypeCode)
            }
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
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeLong(passtime)
//        parcel.writeString(name)
//        parcel.writeString(pass)
//        parcel.writeInt(TypeCode)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<RegisterActivity> {
//        override fun createFromParcel(parcel: Parcel): RegisterActivity {
//            return RegisterActivity(parcel)
//        }
//
//        override fun newArray(size: Int): Array<RegisterActivity?> {
//            return arrayOfNulls(size)
//        }
//    }
}