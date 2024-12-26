package com.example.fitbites.presentation.dashboard

import android.util.Log
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.presentation.profile.ProfileViewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.utils.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    var isDatePickerVisible by remember { mutableStateOf(false) }
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    var currentDate by remember { mutableStateOf("Today") }
    var currentDateMillis: Long? by remember { mutableStateOf(Date().time) }
    var buttonStatus by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Log.d("DATE123", date)
        dashboardViewModel.initializeDailyWaterIntake(date)
        dashboardViewModel.fetchTotalNutrition(date)
    }

    if (isDatePickerVisible) {
        DatePickerModal(
            onDateSelected = { selectedDate ->
                currentDateMillis = selectedDate
                val formattedDate = formatDate(selectedDate)
                Log.d("DATE123", formattedDate.toString())
                dashboardViewModel.fetchTotalNutrition(formattedDate)
                dashboardViewModel.initializeDailyWaterIntake(formattedDate)
                isDatePickerVisible = false
                if (formattedDate == SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())) {
                    currentDate = "Today"
                    buttonStatus = true
                } else {
                    currentDate = formattedDate
                    buttonStatus = false
                }
            },
            onDismiss = {
                isDatePickerVisible = false
            },
            currentDateMillis
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 26.dp, horizontal = 6.dp)
    ) {
        FitbitesmobileTheme(dynamicColor = false) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentDate,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
                IconButton(
                    onClick = {
                        isDatePickerVisible = true
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        Icons.Filled.CalendarMonth,
                        contentDescription = "Calendar",
                        tint = Color.Green
                    )
                }
            }

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
                buttonStatus,
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
                    },
                    enabled = buttonStatus
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    initialDateMillis: Long?
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

fun formatDate(timestamp: Long?): String {
    if (timestamp == null) return ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(timestamp)
}