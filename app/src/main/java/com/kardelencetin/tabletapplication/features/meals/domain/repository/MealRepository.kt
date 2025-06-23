package com.kardelencetin.tabletapplication.features.meals.domain.repository

import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal

interface MealRepository {
    suspend fun getMeals(): List<Meal>
    suspend fun getMealDetail(id: String): Meal?
}