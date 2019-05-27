package com.example.goodscenter.injection.module

import com.example.goodscenter.service.CartService
import dagger.Module
import dagger.Provides

@Module
class CartModule {
    @Provides
    fun ProvidesService(service : CartService):CartService{
        return service
    }
}