package com.example.fitbites.domain.nutrition.model

data class Ingredient(
    val name: String,
    val Calories: Double,
    val Carbs: Double,
    val Proteins: Double,
    val Fats: Double,
    val grams: Double
)

data class TotalMeal(
    val name: String,
    val Fats: Double,
    val Calories: Double,
    val Carbs: Double,
    val Proteins: Double,
    val grams: Double
)

data class ApiResponse(
    val ingredients: Map<String, Ingredient>,
    val total_meal: TotalMeal
)

