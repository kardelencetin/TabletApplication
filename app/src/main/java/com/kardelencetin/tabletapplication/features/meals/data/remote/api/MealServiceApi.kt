package com.kardelencetin.tabletapplication.features.meals.data.remote.api

import com.kardelencetin.tabletapplication.features.meals.data.remote.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealServiceApi {
    @GET("search.php")
    suspend fun getMeals(@Query("s") search: String = ""): MealsResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id: String): MealsResponse
}