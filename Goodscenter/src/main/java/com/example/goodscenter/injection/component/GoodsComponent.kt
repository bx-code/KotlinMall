package com.example.goodscenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.service.GoodsService
import com.example.goodscenter.ui.activity.GoodsLIstActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class))
interface GoodsComponent {
    fun Inject(activity : GoodsLIstActivity)
}