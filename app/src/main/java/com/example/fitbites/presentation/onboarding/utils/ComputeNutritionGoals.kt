package com.example.fitbites.presentation.onboarding.utils

import com.example.fitbites.domain.profile.model.UserProfile
import java.math.BigDecimal
import java.math.RoundingMode

data class DailyMacronutrientsGoal(
    val calories: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0
)

private fun calculateBMR(weightKg: Double, heightCm: Double, ageYears: Int, gender: String): Double {
    val genderConstant = if (gender.lowercase() == "male") 5 else -161
    return 10 * weightKg + 6.25 * heightCm - 5 * ageYears + genderConstant
}

private fun calculateTDEE(bmr: Double, activityLevel: String): Double {
    val activityFactor = when (activityLevel.lowercase()) {
        "sedentary" -> 1.2
        "lightly active" -> 1.375
        "moderately active" -> 1.55
        "very active" -> 1.725
        "extra active" -> 1.9
        else -> 1.2
    }
    return bmr * activityFactor
}

private fun adjustTDEEForGoal(tdee: Double, goal: String): Double {
    return when (goal.lowercase()) {
        "weight loss" -> tdee - 500
        "weight gain" -> tdee + 500
        else -> tdee
    }
}

fun calculateMacronutrientIntake(userProfile: UserProfile): DailyMacronutrientsGoal {

    val bmr = calculateBMR(userProfile.weight.toDouble(), userProfile.height.toDouble(), userProfile.age, userProfile.gender)
    val tdee = calculateTDEE(bmr, userProfile.activityLevel)
    val adjusted_tdee = adjustTDEEForGoal(tdee, userProfile.goal)

    val (proteinRatio, carbRatio, fatRatio) = when (userProfile.goal.lowercase()) {
        "muscle gain" -> Triple(0.30, 0.50, 0.20)  // Protein: 30%, Carbs: 50%, Fats: 20%
        "weight loss" -> Triple(0.35, 0.40, 0.25)  // Protein: 35%, Carbs: 40%, Fats: 25%
        "maintenance" -> Triple(0.25, 0.45, 0.30)  // Protein: 25%, Carbs: 45%, Fats: 30%
        "recomposition" -> Triple(0.35, 0.35, 0.30) // Protein: 35%, Carbs: 35%, Fats: 30%
        else -> Triple(0.25, 0.45, 0.30)
    }

    val proteinKcal = adjusted_tdee * proteinRatio
    val carbKcal = adjusted_tdee * carbRatio
    val fatKcal = adjusted_tdee * fatRatio

    val proteinGrams = (proteinKcal / 4).toInt()
    val carbGrams = (carbKcal / 4).toInt()
    val fatGrams = (fatKcal / 9).toInt()

    return DailyMacronutrientsGoal(
        calories = adjusted_tdee.toInt(),
        protein = proteinGrams,
        carbs = carbGrams,
        fats = fatGrams
    )
}

fun calculateWaterIntake(userProfile: UserProfile): Float {
    val waterIntake = userProfile.weight * 0.033f
    return BigDecimal(waterIntake.toDouble())
        .setScale(1, RoundingMode.HALF_UP)
        .toFloat()
}

