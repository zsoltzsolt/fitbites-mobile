package com.example.fitbites.presentation.onboarding

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun GenderScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedGender = viewModel.userProfile.value.gender

    ConfigurableSelectionScreen (
        title = "What's your gender?",
        subtitle = "Male bodies need more calories",
        options = listOf("Male", "Female"),
        selectedOption = selectedGender,
        onOptionSelected = { selectedOption ->
            viewModel.updateGender(selectedOption)
        },
        onNextClicked = {
            Log.d("USER_PROFILE", "User profile: ${viewModel.userProfile.value}")
            navController.navigate("active")
        }
    )
}
