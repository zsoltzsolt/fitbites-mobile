package com.example.fitbites.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.domain.dashboard.usecase.DashboardUseCases
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardUseCases: DashboardUseCases
) : ViewModel() {

    var waterIntakeState = mutableStateOf(0f)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun fetchCurrentWaterIntake() {
        viewModelScope.launch {
            dashboardUseCases.getCurrentWaterIntake().collect { response ->
                when (response) {
                    is Response.Success -> {
                        waterIntakeState.value = response.data
                        Log.d("Dashboard", "Current water intake fetched: ${response.data} L.")
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                        Log.e("Dashboard", "Failed to fetch water intake: ${response.message}")
                    }
                    is Response.Loading -> {  }
                }
            }
        }
    }

    fun initializeDailyWaterIntake() {
        viewModelScope.launch {
            isLoading.value = true
            dashboardUseCases.initializeDailyWaterIntake().collect { response ->
                when (response) {
                    is Response.Success -> {
                        if (response.data == true) {
                            waterIntakeState.value = 0f
                            Log.d("Dashboard", "Water intake initialized to 0.")
                        } else {
                            fetchCurrentWaterIntake()
                            Log.d("Dashboard", "Water intake already initialized.")
                        }
                    }
                    is Response.Error -> {
                        errorMessage.value = response.message
                        Log.e("Dashboard", "Failed to initialize water intake: ${response.message}")
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
                        waterIntakeState.value += 0.25f
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
                        waterIntakeState.value = (waterIntakeState.value - 0.25f).coerceAtLeast(0f)
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
