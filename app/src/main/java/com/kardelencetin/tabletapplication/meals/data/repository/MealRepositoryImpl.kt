package com.kardelencetin.tabletapplication.features.meals.data.repository

import com.kardelencetin.tabletapplication.features.meals.data.remote.api.MealServiceApi
import com.kardelencetin.tabletapplication.features.meals.data.remote.model.toDomain
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal
import com.kardelencetin.tabletapplication.features.meals.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: MealServiceApi
): MealRepository {
    override suspend fun getMeals(): List<Meal> =
        api.getMeals().meals?.map { it.toDomain() } ?: emptyList()
    override suspend fun getMealDetail(id: String): Meal? =
        api.getMealDetail(id).meals?.firstOrNull()?.toDomain()
}