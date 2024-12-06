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
import androidx.navigation.NavController
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.presentation.profile.ProfileViewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.utils.Response

@Composable
fun Dashboard(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()
    var isDialogVisible by remember { mutableStateOf(false) }
    val userProfileState by profileViewModel.userProfileState.collectAsState()
    val userProfile = getUserProfileFromState(userProfileState)

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
                proteins = 150 to (userProfile?.dailyMacronutrientsGoal?.protein ?: 0),
                fats = 30 to (userProfile?.dailyMacronutrientsGoal?.fats ?: 0),
                carbs = 319 to (userProfile?.dailyMacronutrientsGoal?.carbs ?: 0),
                calories = 2456 to (userProfile?.dailyMacronutrientsGoal?.calories ?: 0)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Water Intake",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            WaterTrackerCard(
                1.9f,
                2.5f,
                "10:45AM",
                {},
                {}
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
                currentBreakfastCalories = 300f,
                currentLunchCalories = 450f,
                currentDinnerCalories = 600f,
                currentSnacksCalories = 150f,
                breakfastTime = "8:00 AM",
                lunchTime = "12:00 PM",
                dinnerTime = "7:00 PM",
                snacksTime = "4:00 PM"
            )
        }
    }

    if (isDialogVisible) {
        AddMealDialog (
            onOptionSelected = { option ->
                isDialogVisible = false
                println("Selected: $option")
            },
            onDismiss = {
                isDialogVisible = false
            },
            navController = navController
        )

fun getUserProfileFromState(state: Response<UserProfile>): UserProfile? {
    return when (state) {
        is Response.Success -> state.data
        else -> null
    }
}