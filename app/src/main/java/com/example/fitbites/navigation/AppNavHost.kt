package com.example.fitbites.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitbites.presentation.splash.SplashScreen
import com.example.fitbites.presentation.auth.LoginScreen
import com.example.fitbites.presentation.auth.SignUpScreen
import com.example.fitbites.presentation.dashboard.Dashboard

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    NavHost(
        modifier = modifier,
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
                onForgotPasswordClick = {  },
                onSignUpClick = {
                    navController.navigate(ROUTE_SIGNUP) {
                    }
                }
            )
        }
        composable(ROUTE_SIGNUP) {
            SignUpScreen(
                onSignUpClick = { },
                onSignInClick = {
                    navController.navigate(ROUTE_LOGIN) {
                    }
                }
            )
        }

        composable(ROUTE_DASHBOARD) {
            Dashboard(
                navController = navController
            )
        }
    }
}