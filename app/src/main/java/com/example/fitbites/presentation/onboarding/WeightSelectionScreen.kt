package com.example.fitbites.presentation.onboarding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.navigation.ROUTE_DASHBOARD
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.presentation.onboarding.utils.calculateMacronutrientIntake
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun WeightSelectionScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedWeight = viewModel.userProfile.value.weight

    FitbitesmobileTheme(dynamicColor = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 60.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ConfigurableHeader(
                    title = "Enter Your Weight",
                    subtitle = "Select your current weight and unit"
                )

                WeightSelectorWithDropdown(
                    weight = selectedWeight.toString(),
                    onWeightUpdated = { weight ->
                        viewModel.updateWeight(weight)
                        viewModel.updateDailyWatersGoal()
                    }
                )
            }

            GradientButton(
                text = "Next",
                onClick = {
                    viewModel.updateSetupStatus(true)
                    viewModel.updateDailyMacronutrientsGoal()
                    viewModel.updateProfile()
                    navController.navigate(ROUTE_DASHBOARD)
                },
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
            )
        }
    }
}