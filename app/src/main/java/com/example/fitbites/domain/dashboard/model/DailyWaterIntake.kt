package com.example.fitbites.domain.dashboard.model

data class DailyWaterIntake(
    val date: String = "",
    val waterIntake: Float = 0f,
    val lastUpdateTime: String = ""
)