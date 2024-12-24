package com.example.fitbites.domain.nutrition.usecase

import com.example.fitbites.domain.dashboard.usecase.FetchTodayTotalNutrition

data class NutritionUseCases(
    val uploadImage: UploadImage,
    val saveMeal: SaveMeal
)
