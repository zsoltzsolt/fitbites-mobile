package com.example.fitbites.domain.nutrition.repository

import com.example.fitbites.domain.nutrition.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Response
import com.example.fitbites.domain.nutrition.model.Ingredient
import com.example.fitbites.domain.nutrition.model.TotalMeal


interface NutritionRepository {
    suspend fun uploadImage(image: MultipartBody.Part): Flow<Response<ApiResponse>>
    suspend fun saveMeal(totalMeal: TotalMeal, ingredients: List<Ingredient>): Flow<com.example.fitbites.utils.Response<Boolean>>
}