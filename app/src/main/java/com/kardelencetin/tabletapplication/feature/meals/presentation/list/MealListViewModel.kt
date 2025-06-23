package com.kardelencetin.tabletapplication.features.meals.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kardelencetin.tabletapplication.features.meals.domain.model.Meal
import com.kardelencetin.tabletapplication.features.meals.domain.usecase.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    init { fetchMeals() }
    private fun fetchMeals() {
        viewModelScope.launch {
            _meals.value = getMealsUseCase()
        }
    }
}