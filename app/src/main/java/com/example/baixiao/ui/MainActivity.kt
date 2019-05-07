package com.example.baixiao.ui

import android.app.Activity
import android.app.FragmentManager
import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.example.baixiao.injection.module.AppModule
import com.example.baixiao.twokotlinmall.FirstClass
import com.example.baixiao.twokotlinmall.R
import com.example.baixiao.ui.fragment.HomeFragment
import com.example.baixiao.ui.fragment.MeFragment
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.goodscenter.ui.fragment.CategoryFramgment
import com.example.usercenter.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity :BaseActivity() {

    @Inject
    lateinit var fitst : FirstClass

    val fragmentStack : Stack<BaseFragment> = Stack()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFramgment by lazy { CategoryFramgment() }
//    private val mHomeFragment1 by lazy { HomeFragment() }
    private val mHomeFragment2 by lazy { HomeFragment() }
    private val mHomeFragment3 by lazy { HomeFragment() }
    private val mmeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNav.checkCartBadge(20)
        mBottomNav.checkMsgBadge(false)

//        Observable.timer(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({mBottomNav.checkMsgBadge(true)})
//
//        Observable.timer(4,TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({mBottomNav.checkCartBadge(0)})
        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    private fun initBottomNav() {
        mBottomNav.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })

        mBottomNav.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for(fragmer in fragmentStack){
            manager.hide(fragmer)
        }
        manager.show(fragmentStack[position])
        manager.commit()
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mframelayout,mHomeFragment)
        manager.add(R.id.mframelayout,mCategoryFramgment)
        manager.add(R.id.mframelayout,mHomeFragment2)
        manager.add(R.id.mframelayout,mHomeFragment3)
        manager.add(R.id.mframelayout,mmeFragment)
        manager.commit()

        fragmentStack.add(mHomeFragment)
        fragmentStack.add(mCategoryFramgment)
        fragmentStack.add(mHomeFragment2)
        fragmentStack.add(mHomeFragment3)
        fragmentStack.add(mmeFragment)
    }

}
