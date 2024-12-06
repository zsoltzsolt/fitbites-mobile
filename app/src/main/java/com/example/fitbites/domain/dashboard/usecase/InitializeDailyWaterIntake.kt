package com.example.fitbites.domain.dashboard.usecase

import com.example.fitbites.domain.dashboard.repository.DashboardRepository
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

class InitializeDailyWaterIntake(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): Flow<Response<Boolean>> {
        return repository.initializeDailyWaterIntake()
    }
}
