package com.example.fitbites.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.domain.dashboard.model.DailyNutritionWithBreakdown
import com.example.fitbites.domain.dashboard.usecase.DashboardUseCases
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCases: DashboardUseCases
) : ViewModel() {

    var waterIntakeState = mutableStateOf(0f)
        private set

    var todayTotalNutritionWithBreakdown = mutableStateOf(DailyNutritionWithBreakdown())
        private set

    var lastUpdateTime = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun fetchCurrentWaterIntake(date: String) {
        viewModelScope.launch {
            dashboardUseCases.getCurrentWaterIntake(date).collect { response ->
                when (response) {
                    is Response.Success -> {
                        waterIntakeState.value = response.data.waterIntake
                        lastUpdateTime.value = response.data.lastUpdateTime
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                    }
                    is Response.Loading -> {  }
                }
            }
        }
    }

    fun fetchTotalNutrition(date: String) {
        viewModelScope.launch {
            dashboardUseCases.fetchTodayTotalNutritionWithBreakdown(date).collect { response ->
                when (response) {
                    is Response.Success -> {
                        Log.d("RESPONSE!!!", response.data.toString())
                        todayTotalNutritionWithBreakdown.value = response.data
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                    }
                    is Response.Loading -> {  }
                }
            }
        }
    }

    fun initializeDailyWaterIntake(date: String) {
        viewModelScope.launch {
            isLoading.value = true
            dashboardUseCases.initializeDailyWaterIntake().collect { response ->
                when (response) {
                    is Response.Success -> {
                        if (response.data == true) {
                            waterIntakeState.value = 0f
                            lastUpdateTime.value = ""
                        } else {
                            fetchCurrentWaterIntake(date)
                        }
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                    }
                    is Response.Loading -> {  }
                }
                isLoading.value = false
            }
        }
    }

    fun incrementDailyWaterIntake() {
        viewModelScope.launch {
            isLoading.value = true
            dashboardUseCases.incrementDailyWaterIntake().collect { response ->
                when (response) {
                    is Response.Success -> {
                        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                        waterIntakeState.value += 0.25f
                        lastUpdateTime.value = currentTime
                        Log.d("Dashboard", "Water intake incremented.")
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                        Log.e("Dashboard", "Failed to increment water intake: ${response.message}")
                    }
                    is Response.Loading -> {  }
                }
                isLoading.value = false
            }
        }
    }

    fun decrementDailyWaterIntake() {
        viewModelScope.launch {
            isLoading.value = true
            dashboardUseCases.decrementDailyWaterIntake().collect { response ->
                when (response) {
                    is Response.Success -> {
                        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                        waterIntakeState.value = (waterIntakeState.value - 0.25f).coerceAtLeast(0f)
                        lastUpdateTime.value = currentTime
                        Log.d("Dashboard", "Water intake decremented.")
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                        Log.e("Dashboard", "Failed to decrement water intake: ${response.message}")
                    }
                    is Response.Loading -> {  }
                }
                isLoading.value = false
            }
        }
    }
}
