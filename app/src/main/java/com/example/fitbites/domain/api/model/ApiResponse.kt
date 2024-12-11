package com.example.fitbites.domain.api.model

data class ApiResponse(
    val ingredients: Map<String, Ingredient>,
    val total_meal: TotalMeal
)

data class Ingredient(
    val Carbs: Double,
    val Proteins: Double,
    val Calories: Double,
    val Fats: Double,
    val grams: Int
)

data class TotalMeal(
    val Carbs: Double,
    val Proteins: Double,
    val Calories: Double,
    val Fats: Double
)
