package com.example.fitbites.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.presentation.profile.ProfileViewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.utils.Response

@Composable
fun Dashboard(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()
    var isDialogVisible by remember { mutableStateOf(false) }
    val userProfileState by profileViewModel.userProfileState.collectAsState()
    val userProfile = getUserProfileFromState(userProfileState)
    val waterIntake by dashboardViewModel.waterIntakeState
    val lastUpdateTime by dashboardViewModel.lastUpdateTime
    val todayTotalNutritionWithBreakdown by dashboardViewModel.todayTotalNutritionWithBreakdown

    LaunchedEffect(Unit) {
        dashboardViewModel.initializeDailyWaterIntake()
        dashboardViewModel.fetchTodayTotalNutrition()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 26.dp, horizontal = 6.dp)
    ) {
        FitbitesmobileTheme(dynamicColor = false) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Summary",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            SummaryCard(
                proteins = todayTotalNutritionWithBreakdown.overall.Proteins.toInt()  to (userProfile?.dailyMacronutrientsGoal?.protein ?: 0),
                fats = todayTotalNutritionWithBreakdown.overall.Fats.toInt() to (userProfile?.dailyMacronutrientsGoal?.fats ?: 0),
                carbs = todayTotalNutritionWithBreakdown.overall.Carbs.toInt() to (userProfile?.dailyMacronutrientsGoal?.carbs ?: 0),
                calories = todayTotalNutritionWithBreakdown.overall.Calories.toInt() to (userProfile?.dailyMacronutrientsGoal?.calories ?: 0)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Water Intake",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            WaterTrackerCard(
                waterIntake,
                (userProfile?.dailyWaterGoal ?: 2.5).toFloat(),
                lastUpdateTime,
                {dashboardViewModel.incrementDailyWaterIntake()},
                {dashboardViewModel.decrementDailyWaterIntake()}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Meals",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
                IconButton(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    onClick = {
                        isDialogVisible = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            MealTrackerCard(
                currentBreakfastCalories = todayTotalNutritionWithBreakdown.meals["Breakfast"]?.Calories?.toFloat() ?: 0f,
                currentLunchCalories = todayTotalNutritionWithBreakdown.meals["Lunch"]?.Calories?.toFloat() ?: 0f,
                currentDinnerCalories = todayTotalNutritionWithBreakdown.meals["Dinner"]?.Calories?.toFloat() ?: 0f,
                currentSnacksCalories = todayTotalNutritionWithBreakdown.meals["Snacks"]?.Calories?.toFloat() ?: 0f,
                breakfastTime = "8:00 AM",
                lunchTime = "12:00 PM",
                dinnerTime = "7:00 PM",
                snacksTime = "4:00 PM"
            )
        }
    }

    if (isDialogVisible) {
        AddMealDialog(
            onOptionSelected = { option ->
                isDialogVisible = false
                println("Selected: $option")
            },
            onDismiss = {
                isDialogVisible = false
            },
            navController = navController
        )
    }
}
fun getUserProfileFromState(state: Response<UserProfile>): UserProfile? {
    return when (state) {
        is Response.Success -> state.data
        else -> null
    }
}
