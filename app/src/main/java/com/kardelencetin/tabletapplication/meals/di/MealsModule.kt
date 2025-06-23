package com.kardelencetin.tabletapplication.features.meals.di

import com.kardelencetin.tabletapplication.features.meals.data.remote.api.MealServiceApi
import com.kardelencetin.tabletapplication.features.meals.data.repository.MealRepositoryImpl
import com.kardelencetin.tabletapplication.features.meals.domain.repository.MealRepository
import com.kardelencetin.tabletapplication.features.meals.domain.usecase.GetMealDetailUseCase
import com.kardelencetin.tabletapplication.features.meals.domain.usecase.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MealsModule {

    @Provides
    fun provideMealRepository(api: MealServiceApi): MealRepository = MealRepositoryImpl(api)

    @Provides
    fun provideGetMealsUseCase(repo: MealRepository) = GetMealsUseCase(repo)

    @Provides
    fun provideGetMealDetailUseCase(repo: MealRepository) = GetMealDetailUseCase(repo)
}