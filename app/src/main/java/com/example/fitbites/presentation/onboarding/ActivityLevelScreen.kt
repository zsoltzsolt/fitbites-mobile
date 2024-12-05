package com.example.fitbites.presentation.onboarding

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ActivityLevelScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedActivityLevel = viewModel.userProfile.value.activityLevel

    ConfigurableSelectionScreen (
        title = "How active are you?",
        subtitle = "A sedentary person burns fewer calories than an active person",
        options = listOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Extra Active"),
        selectedOption = selectedActivityLevel,
        onOptionSelected = { selectedOption ->
            viewModel.updateActivityLevel(selectedOption)
        },
        onNextClicked = {
            Log.d("USER_PROFILE", "User profile: ${viewModel.userProfile.value}")
            navController.navigate("age")
        }
    )
}
