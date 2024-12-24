package com.example.fitbites.domain.dashboard.model

data class DailyNutritionWithBreakdown(
    val overall: DailyNutrition = DailyNutrition(),
    val meals: Map<String, DailyNutrition> = emptyMap()
)