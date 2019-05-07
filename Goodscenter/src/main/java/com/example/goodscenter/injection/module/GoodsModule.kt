package com.example.goodscenter.injection.module

import com.example.goodscenter.service.GoodsService
import dagger.Module
import dagger.Provides

@Module
class GoodsModule {
    @Provides
    fun ProvidesService(Service : GoodsService) : GoodsService{
        return Service
    }
}