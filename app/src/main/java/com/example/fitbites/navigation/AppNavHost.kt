package com.example.fitbites.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitbites.SplashScreen
import com.example.fitbites.auth.presentation.LoginScreen
import com.example.fitbites.auth.presentation.SignUpScreen

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
                onSignInClick = { },
                onForgotPasswordClick = {  },
                onSignUpClick = { navController.navigate(ROUTE_SIGNUP) }
            )
        }
        composable(ROUTE_SIGNUP) {
            SignUpScreen(
                onSignUpClick = { },
                onSignInClick = { navController.navigate(ROUTE_LOGIN) }
            )
        }
    }
}