package com.example.goodscenter.injection.module

import com.example.goodscenter.service.CategoryService
import dagger.Module
import dagger.Provides


@Module
class CatGoryModule {
    @Provides
    fun ProvidesService(service:CategoryService):CategoryService{
            return service
    }
}