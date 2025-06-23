package com.kardelencetin.tabletapplication.features.meals.domain.usecase

import com.kardelencetin.tabletapplication.features.meals.domain.repository.MealRepository
import javax.inject.Inject

class GetMealDetailUseCase @Inject constructor(
    private val repo: MealRepository
) {
    suspend operator fun invoke(id: String) = repo.getMealDetail(id)
}