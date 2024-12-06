package com.example.fitbites.domain.dashboard.repository

import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    suspend fun initializeDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun incrementDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun decrementDailyWaterIntake(): Flow<Response<Boolean>>
    suspend fun getCurrentWaterIntake(): Flow<Response<Float>>
}