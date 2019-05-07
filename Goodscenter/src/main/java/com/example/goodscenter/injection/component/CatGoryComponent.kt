package com.example.goodscenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.injection.module.CatGoryModule
import com.example.goodscenter.ui.fragment.CategoryFramgment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CatGoryModule::class))
interface  CatGoryComponent {
    fun Inject(framgment: CategoryFramgment)
}