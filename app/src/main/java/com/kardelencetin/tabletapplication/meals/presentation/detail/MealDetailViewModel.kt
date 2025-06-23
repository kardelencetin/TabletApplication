package com.kardelencetin.tabletapplication.features.meals.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal
import com.kardelencetin.tabletapplication.features.meals.domain.usecase.GetMealDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getMealDetailUseCase: GetMealDetailUseCase
) : ViewModel() {
    private val _meal = MutableLiveData<Meal?>()
    val meal: LiveData<Meal?> = _meal

    fun fetchMeal(id: String) {
        viewModelScope.launch {
            _meal.value = getMealDetailUseCase(id)
        }
    }
}