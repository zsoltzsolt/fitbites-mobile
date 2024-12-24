package com.example.fitbites.domain.dashboard.usecase

import com.example.fitbites.domain.dashboard.model.DailyNutrition
import com.example.fitbites.domain.dashboard.repository.DashboardRepository
import com.example.fitbites.repository.dashboard.DashboardRepositoryImpl
import com.example.fitbites.repository.nutrition.NutritionRepositoryImpl
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTodayTotalNutrition @Inject constructor(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): Flow<Response<DailyNutrition>> {
        return repository.fetchTodayTotalNutrition()
    }
}