package com.example.fitbites.repository.dashboard

import com.example.fitbites.domain.dashboard.model.DailyNutrition
import com.example.fitbites.domain.dashboard.model.DailyNutritionWithBreakdown
import com.example.fitbites.domain.dashboard.model.DailyWaterIntake
import com.example.fitbites.domain.dashboard.repository.DashboardRepository
import com.example.fitbites.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): DashboardRepository {

    override suspend fun initializeDailyWaterIntake(): Flow<Response<Boolean>> = flow {
        val userId = auth.currentUser?.uid ?: return@flow emit(Response.Error("User not authenticated"))
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val waterIntakeRef = firestore.collection("users").document(userId)
            .collection("waterIntakeHistory").document(date)

        try {
            val documentSnapshot = waterIntakeRef.get().await()
            if (!documentSnapshot.exists()) {
                val initialData = DailyWaterIntake(
                        date = date,
                        waterIntake = 0f,
                        lastUpdateTime = ""
                        )
                waterIntakeRef.set(initialData).await()
                emit(Response.Success(true))
            } else {
                emit(Response.Success(false))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to initialize water intake"))
        }
    }



    override suspend fun incrementDailyWaterIntake(): Flow<Response<Boolean>> = flow {
        val userId = auth.currentUser?.uid ?: return@flow emit(Response.Error("User not authenticated"))
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val waterIntakeRef = firestore.collection("users").document(userId)
            .collection("waterIntakeHistory").document(date)

        try {
            val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(waterIntakeRef)
                val currentIntake = snapshot.getDouble("waterIntake") ?: 0.0
                val updatedIntake = currentIntake + 0.25
                transaction.set(
                    waterIntakeRef,
                    DailyWaterIntake(
                        date = date,
                        waterIntake = updatedIntake.toFloat(),
                        lastUpdateTime = currentTime
                    ),
                    SetOptions.merge()
                )
            }.await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to increment water intake"))
        }
    }

    override suspend fun decrementDailyWaterIntake(): Flow<Response<Boolean>> = flow {
        val userId = auth.currentUser?.uid ?: return@flow emit(Response.Error("User not authenticated"))
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val waterIntakeRef = firestore.collection("users").document(userId)
            .collection("waterIntakeHistory").document(date)

        try {
            val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(waterIntakeRef)
                val currentIntake = snapshot.getDouble("waterIntake") ?: 0.0
                val updatedIntake = (currentIntake - 0.25).coerceAtLeast(0.0)
                transaction.set(
                    waterIntakeRef,
                    DailyWaterIntake(
                        date = date,
                        waterIntake = updatedIntake.toFloat(),
                        lastUpdateTime = currentTime
                    ),
                    SetOptions.merge()
                )
            }.await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to decrement water intake"))
        }
    }

    override suspend fun getCurrentWaterIntake(): Flow<Response<DailyWaterIntake>> = flow {
        val userId = auth.currentUser?.uid ?: return@flow emit(Response.Error("User not authenticated"))
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val waterIntakeRef = firestore.collection("users").document(userId)
            .collection("waterIntakeHistory").document(date)

        try {
            val documentSnapshot = waterIntakeRef.get().await()

            if (documentSnapshot.exists()) {
                val waterIntake = documentSnapshot.getDouble("waterIntake")?.toFloat() ?: 0f
                val lastUpdateTime = documentSnapshot.getString("lastUpdateTime") ?: "Not available"

                val dailyWaterIntake = DailyWaterIntake(
                    date = date,
                    waterIntake = waterIntake,
                    lastUpdateTime = lastUpdateTime
                )
                emit(Response.Success(dailyWaterIntake))
            } else {
                emit(Response.Success(DailyWaterIntake()))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to fetch current water intake"))
        }
    }

    override suspend fun fetchTodayTotalNutritionWithBreakdown(): Flow<Response<DailyNutritionWithBreakdown>> = flow {
        val userId = auth.currentUser?.uid
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        if (userId == null) {
            emit(Response.Error("User not authenticated"))
            return@flow
        }

        try {
            val mealsSnapshot = firestore.collection("users").document(userId)
                .collection("mealHistory").document(date)
                .collection("meals")
                .get()
                .await()

            var totalCalories = 0.0
            var totalCarbs = 0.0
            var totalProteins = 0.0
            var totalFats = 0.0

            val mealBreakdown = mutableMapOf<String, DailyNutrition>()

            mealBreakdown["Breakfast"] = DailyNutrition()
            mealBreakdown["Lunch"] = DailyNutrition()
            mealBreakdown["Dinner"] = DailyNutrition()
            mealBreakdown["Snacks"] = DailyNutrition()

            for (mealDoc in mealsSnapshot.documents) {
                val calories = mealDoc.getDouble("calories") ?: 0.0
                val carbs = mealDoc.getDouble("carbs") ?: 0.0
                val proteins = mealDoc.getDouble("proteins") ?: 0.0
                val fats = mealDoc.getDouble("fats") ?: 0.0
                val time = mealDoc.getString("time") ?: "00:00"

                val hour = time.split(":")[0].toInt()
                val mealType = when (hour) {
                    in 7..11 -> "Breakfast"
                    in 13..16 -> "Lunch"
                    in 18..21 -> "Dinner"
                    else -> "Snacks"
                }

                val currentMealNutrition = mealBreakdown[mealType] ?: DailyNutrition()
                mealBreakdown[mealType] = currentMealNutrition.copy(
                    Calories = currentMealNutrition.Calories + calories,
                    Carbs = currentMealNutrition.Carbs + carbs,
                    Proteins = currentMealNutrition.Proteins + proteins,
                    Fats = currentMealNutrition.Fats + fats
                )

                totalCalories += calories
                totalCarbs += carbs
                totalProteins += proteins
                totalFats += fats
            }

            val dailyNutritionWithBreakdown = DailyNutritionWithBreakdown(
                overall = DailyNutrition(
                    Calories = totalCalories,
                    Carbs = totalCarbs,
                    Proteins = totalProteins,
                    Fats = totalFats
                ),
                meals = mealBreakdown
            )

            emit(Response.Success(dailyNutritionWithBreakdown))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to fetch daily nutrition with breakdown"))
        }
    }


}
