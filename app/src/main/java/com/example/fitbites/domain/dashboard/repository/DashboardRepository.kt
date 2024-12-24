package com.example.fitbites.domain.dashboard.repository

import com.example.fitbites.domain.dashboard.model.DailyNutrition
import com.example.fitbites.domain.dashboard.model.DailyNutritionWithBreakdown
import com.example.fitbites.domain.dashboard.model.DailyWaterIntake
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    suspend fun initializeDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun incrementDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun decrementDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun getCurrentWaterIntake(): Flow<Response<DailyWaterIntake>>
    suspend fun fetchTodayTotalNutritionWithBreakdown(): Flow<Response<DailyNutritionWithBreakdown>>
}