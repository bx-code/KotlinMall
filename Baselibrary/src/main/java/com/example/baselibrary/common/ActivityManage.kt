package com.example.baselibrary.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by baixiao on 2019/4/3.
 * 创建 activity Stack 栈
 */

class ActivityManage private constructor(){

    /**
     * 创建activity stack
     */
    val activityStack : Stack<Activity> = Stack()

    companion object {
        val instance : ActivityManage by lazy { ActivityManage() }
    }

    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    //选择stack位于栈顶的activity
    fun currentActivity(activity: Activity){
        activityStack.lastElement()
    }

    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }
    //退出整个应用
    @SuppressLint("ServiceCast")
    fun appExit(context: Context){
        finishAllActivity()
        val  activityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}

