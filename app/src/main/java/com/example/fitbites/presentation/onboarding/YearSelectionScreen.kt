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
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun YearsSelectionScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navController: NavController
) {
    val selectedYear = viewModel.userProfile.value.age

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
                    title = "Enter Your Age",
                    subtitle = "Provide your age in years"
                )

                YearsInputField(
                    initialYear = selectedYear.toString(),
                    onYearChanged = { year -> viewModel.updateAge(year) }
                )
            }

            GradientButton(
                text = "Next",
                onClick = {
                    Log.d("USER_PROFILE", "User profile: ${viewModel.userProfile.value}")
                    navController.navigate("weight")
                },
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
            )
        }
    }
}


