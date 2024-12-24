package com.example.fitbites.repository.nutrition

import com.example.fitbites.domain.nutrition.model.ApiResponse
import com.example.fitbites.domain.nutrition.model.Ingredient
import com.example.fitbites.domain.nutrition.model.TotalMeal
import com.example.fitbites.domain.nutrition.repository.NutritionRepository
import com.example.fitbites.network.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : NutritionRepository {
    override suspend fun uploadImage(image: MultipartBody.Part): Flow<Response<ApiResponse>> = flow {
        val response = RetrofitInstance.api.uploadImage(image)
        emit(response)
    }

    override suspend fun saveMeal(
        totalMeal: TotalMeal,
        ingredients: List<Ingredient>
    ): Flow<com.example.fitbites.utils.Response<Boolean>> = flow {
        val userId = auth.currentUser?.uid
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        val mealId = SimpleDateFormat("HHmmss", Locale.getDefault()).format(Date())

        val mealRef = firestore.collection("users").document(userId!!)
            .collection("mealHistory").document(date)
            .collection("meals").document(mealId)
        val ingredientsRef = mealRef.collection("ingredients")

        try {
            val mealData = mapOf(
                "name" to totalMeal.name,
                "calories" to totalMeal.Calories,
                "carbs" to totalMeal.Carbs,
                "proteins" to totalMeal.Proteins,
                "fats" to totalMeal.Fats,
                "grams" to totalMeal.grams,
                "time" to currentTime
            )
            mealRef.set(mealData).await()

            ingredients.forEach { ingredient ->
                val ingredientData = mapOf(
                    "name" to ingredient.name,
                    "calories" to ingredient.Calories,
                    "carbs" to ingredient.Carbs,
                    "proteins" to ingredient.Proteins,
                    "fats" to ingredient.Fats,
                    "grams" to ingredient.grams
                )
                ingredientsRef.add(ingredientData).await()
            }

            emit(com.example.fitbites.utils.Response.Success(true))
        } catch (e: Exception) {
            emit(com.example.fitbites.utils.Response.Error(e.message ?: "Failed to save meal"))
        }
    }

}

