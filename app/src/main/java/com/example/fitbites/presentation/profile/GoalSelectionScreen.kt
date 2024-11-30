package com.example.fitbites.presentation.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun GoalScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedGoal = viewModel.userProfile.value.goal

    ConfigurableSelectionScreen(
        title = "What's your goal?",
        subtitle = "We will calculate daily calories according to your goal",
        options = listOf("Lose weight", "Keep weight", "Gain weight"),
        selectedOption = selectedGoal,
        onOptionSelected = { selectedOption ->
            viewModel.updateGoal(selectedOption)
        },
        onNextClicked = {
            Log.d("USER_PROFILE", "User profile: ${viewModel.userProfile.value}")
            navController.navigate("gender")
        }
    )
}
