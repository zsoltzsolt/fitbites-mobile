package com.example.fitbites.domain.dashboard.usecase

data class DashboardUseCases (
    val initializeDailyWaterIntake: InitializeDailyWaterIntake,
    val incrementDailyWaterIntake: IncrementDailyWaterIntake,
    val decrementDailyWaterIntake: DecrementDailyWaterIntake,
    val getCurrentWaterIntake: GetCurrentWaterIntake
)