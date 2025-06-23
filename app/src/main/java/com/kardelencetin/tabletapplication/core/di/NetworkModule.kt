package com.kardelencetin.tabletapplication.core.di

import com.kardelencetin.tabletapplication.core.util.Constants.BASE_URL
import com.kardelencetin.tabletapplication.features.meals.data.remote.api.MealServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideMealApiService(retrofit: Retrofit): MealServiceApi =
        retrofit.create(MealServiceApi::class.java)
}