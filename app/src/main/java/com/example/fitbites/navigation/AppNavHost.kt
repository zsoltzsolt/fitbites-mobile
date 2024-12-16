package com.example.fitbites.navigation

import CameraScreen
import SplashScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.fitbites.presentation.auth.LoginScreen
import com.example.fitbites.presentation.auth.SignUpScreen
import com.example.fitbites.presentation.dashboard.Dashboard
import com.example.fitbites.presentation.navigation.BottomNavigationBar
import com.example.fitbites.presentation.nutritionBreakdown.FoodAnalysisScreen
import com.example.fitbites.presentation.onboarding.ActivityLevelScreen
import com.example.fitbites.presentation.onboarding.GenderScreen
import com.example.fitbites.presentation.onboarding.GoalScreen
import com.example.fitbites.presentation.onboarding.OnboardingViewModel
import com.example.fitbites.presentation.onboarding.WeightSelectionScreen
import com.example.fitbites.presentation.onboarding.YearsSelectionScreen
import com.example.fitbites.presentation.profile.ProfileScreen
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == ROUTE_DASHBOARD || currentRoute == ROUTE_PROFILE) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(ROUTE_SPLASH) {
                SplashScreen(
                    navController = navController
                )
            }
            composable(ROUTE_LOGIN) {
                LoginScreen(
                    navController = navController,
                    onSignInClick = { },
                    onForgotPasswordClick = { }
                )
            }
            composable(ROUTE_SIGNUP) {
                SignUpScreen(
                    onboardingViewModel = onboardingViewModel,
                    navController = navController,
                    onSignUpClick = { }
                )
            }

            composable(ROUTE_DASHBOARD) {
                Dashboard(
                    navController = navController
                )
            }

            composable(ROUTE_GOAL) {
                GoalScreen(
                    viewModel = onboardingViewModel,
                    navController = navController
                )
            }

            composable(ROUTE_GENDER) {
                GenderScreen(
                    viewModel = onboardingViewModel,
                    navController = navController
                )
            }

            composable(ROUTE_ACTIVITY_LEVEL) {
                ActivityLevelScreen(
                    viewModel = onboardingViewModel,
                    navController = navController
                )
            }

            composable(ROUTE_AGE) {
                YearsSelectionScreen(
                    viewModel = onboardingViewModel,
                    navController = navController
                )
            }

            composable(ROUTE_WEIGHT) {
                WeightSelectionScreen(
                    viewModel = onboardingViewModel,
                    navController = navController
                )
            }

            composable(ROUTE_PROFILE) {
                ProfileScreen(
                    navController = navController
                )
            }

            composable("camera") {
                CameraScreen(navController)
            }

            composable(ROUTE_ANALYSIS){

                FitbitesmobileTheme(dynamicColor = false) {
                    FoodAnalysisScreen(navController)
                }
            }

        }
    }
}
