package com.example.fitbites.navigation

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
import com.example.fitbites.presentation.splash.SplashScreen
import com.example.fitbites.presentation.auth.LoginScreen
import com.example.fitbites.presentation.auth.SignUpScreen
import com.example.fitbites.presentation.dashboard.Dashboard
import com.example.fitbites.presentation.navigation.BottomNavigationBar
import com.example.fitbites.presentation.profile.ActivityLevelScreen
import com.example.fitbites.presentation.profile.GenderScreen
import com.example.fitbites.presentation.profile.GoalScreen
import com.example.fitbites.presentation.profile.ProfileViewModel
import com.example.fitbites.presentation.profile.WeightSelectionScreen
import com.example.fitbites.presentation.profile.YearsSelectionScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == ROUTE_DASHBOARD) {
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
                SplashScreen {
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_SPLASH) { inclusive = true }
                    }
                }
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
                    profileViewModel = profileViewModel,
                    navController = navController,
                    onSignUpClick = { }
                )
            }

            composable(ROUTE_DASHBOARD) {
                Dashboard(
                    navController = navController
                )
            }

            composable("goal") {
                GoalScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }

            composable("gender") {
                GenderScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }

            composable("active") {
                ActivityLevelScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }

            composable("age") {
                YearsSelectionScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }

            composable("weight") {
                WeightSelectionScreen(
                    viewModel = profileViewModel,
                    navController = navController
                )
            }
        }
    }
}
