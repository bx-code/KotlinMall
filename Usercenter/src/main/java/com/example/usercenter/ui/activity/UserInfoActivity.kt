package com.example.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.baselibrary.common.ActivityManage
import com.example.baselibrary.ext.OnClick
import com.example.baselibrary.ui.activity.BaseMVPActivity
import com.example.provider.common.ProviderConstant
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.Usermodule
import com.example.usercenter.ui.presenter.UserInfoPresenter
import com.example.usercenter.ui.presenter.view.UserInfoView
import com.example.usercenter.utils.UserPrefsUtils
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.model.TResult
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.register_layout.*
import org.jetbrains.anko.toast
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.ui.activity.ResetPwdActivity
import com.qiniu.android.storage.UploadManager
import java.io.File


/**
 * Created by baixiao on 2019/3/30.
 */

class UserInfoActivity : BaseMVPActivity<UserInfoPresenter>(), UserInfoView,TakePhoto.TakeResultListener{

    var timelong : Long =0
    private lateinit var  mTakePhoto: TakePhoto
    private lateinit var  mTempFile : File
    private val mUploadManager: UploadManager by lazy { UploadManager() }
    private lateinit var  mLocalFileUrl: String
    private lateinit var  mRemoteFileUrl:String
    /**
     * UI
     */
    private var userIcon:String?=null
    private var userName:String?=null
    private var userGender:String?=null
    private var userMobile:String?=null
    private var userSign:String?=null

    //调用实例化后的dagger2对象
    override fun initDaggerTwo() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .usermodule(Usermodule()).build().Inject(this)
        mPresenter.mView=this
    }


    lateinit var name:String
    lateinit var pass:String
    var TypeCode=1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        mUserIconIv.OnClick {
            AlertImage()
        }
        mHeaderBar.getRightView().OnClick {
            mPresenter.saveUserInfo(mUserNameEt.text.toString(),if(mGenderMaleRb.isChecked) "0" else "1",mUserSignEt.text.toString())
        }
        infoview()
    }

    private fun infoview(){
        userIcon=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        userName=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        userGender=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        userMobile=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        userSign=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        GlideUtils.loadImage(this,userIcon!!,mUserIconIv)
        mUserNameEt.setText(userName)
        if(userGender=="0") mGenderMaleRb.isChecked=true else mGenderFemaleRb.isChecked=true
        mUserMobileTv.setText(userMobile)
        mUserSignEt.setText(userSign)
    }

    private fun AlertImage() {
        AlertView("上传图片",null,"取消",null, arrayOf("拍照","从相册中选择"),this,
                    AlertView.Style.ActionSheet,object : OnItemClickListener{
            override fun onItemClick(o: Any?, position: Int) {
                    //压缩图片 剪切图片
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                    when(position){
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> {
                            mTakePhoto.onPickFromGallery()
                        }

                    }
            }
        }).show()
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

    //拿到图片地址 获取图片成功
    override fun takeSuccess(result: TResult?) {
        Log.e("TakePhoto_originalPath",result?.image?.originalPath)
        Log.e("TakePhoto_compressPath",result?.image?.compressPath)
        mLocalFileUrl=result?.image?.originalPath!!
        mPresenter.Upload()
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto",msg)
    }


    fun createTempFile(){
            val tempFile = "${DateUtils.curTime}.png"
            //判断是否有sd卡
            if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                 //拿到sd卡 file地址
                 this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFile)
                 return
            }
            //没有sd卡 直接从内存中获取
            this.mTempFile = File(filesDir,tempFile)
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

    override fun loadUserInfo(userInfo: UserInfo) {
            UserPrefsUtils.putUserInfo(userInfo)
            toast("保存成功")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }


    /**
     * 模拟图片上传 并显示给imageivew
     */
    override fun upload(reselt: String) {
        Log.e("TakeLoad","图片获取地址${reselt}")
        GlideUtils.loadImage(this@UserInfoActivity,mLocalFileUrl,mUserIconIv)
//        mUploadManager.put(mLocalFileUrl,null,reselt,object: UpCompletionHandler {
//            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
//                mRemoteFileUrl = Constant.IMAGE_SERVER_ADDRESS + response?.get("hash")
//                Log.d("test", mRemoteFileUrl)
//                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!,mUserIconIv)
//            }
//
//        },null)
    }

}