package com.example.fitbites.domain.dashboard.usecase

import com.example.fitbites.domain.dashboard.model.MealBreakdown

data class DashboardUseCases (
    val initializeDailyWaterIntake: InitializeDailyWaterIntake,
    val incrementDailyWaterIntake: IncrementDailyWaterIntake,
    val decrementDailyWaterIntake: DecrementDailyWaterIntake,
    val getCurrentWaterIntake: GetCurrentWaterIntake,
    val fetchTodayTotalNutritionWithBreakdown: FetchTodayTotalNutritionWithBreakdown
)