package com.example.fitbites.domain.dashboard.usecase

import com.example.fitbites.domain.dashboard.model.DailyWaterIntake
import com.example.fitbites.domain.dashboard.repository.DashboardRepository
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

class GetCurrentWaterIntake(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(date: String): Flow<Response<DailyWaterIntake>> {
        return repository.getCurrentWaterIntake(date)
    }
}
