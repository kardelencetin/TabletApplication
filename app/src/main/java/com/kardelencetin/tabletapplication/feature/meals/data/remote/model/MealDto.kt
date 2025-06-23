package com.kardelencetin.tabletapplication.features.meals.data.remote.model

import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal

data class MealsResponse(val meals: List<MealDto>?)

data class MealDto(
    val idMeal: String,
    val strMeal: String,
    val strInstructions: String,
    val strMealThumb: String
)

fun MealDto.toDomain() = Meal(
    id = idMeal,
    title = strMeal,
    description = strInstructions,
    image = strMealThumb
)