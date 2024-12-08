package com.example.fitbites.domain.profile.model

import com.example.fitbites.presentation.onboarding.utils.DailyMacronutrientsGoal

data class UserProfile(
    val email: String = "",
    val name: String = "",
    val setupComplete: Boolean = false,
    var goal: String = "",
    var gender: String = "",
    var activityLevel: String = "",
    var height: Int = 170,
    var weight: Int = 0,
    var age: Int = 0,
    var dailyMacronutrientsGoal: DailyMacronutrientsGoal = DailyMacronutrientsGoal(),
    var dailyWaterGoal: Float = 2.5f
)
