package com.example.fitbites.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.example.fitbites.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.navigation.ROUTE_DASHBOARD
import com.example.fitbites.presentation.components.AuthFooter
import com.example.fitbites.presentation.components.AuthHeader
import com.example.fitbites.presentation.components.AuthTextField
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.presentation.components.SocialMediaSection
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isUserAuthenticated = authViewModel.isUserAuthenticatedState.value
    LaunchedEffect(Unit) {
        if (isUserAuthenticated) {
            navController.navigate(ROUTE_DASHBOARD)
        }
    }

    val isUserSignIn = authViewModel.isUserSignInState.value
    LaunchedEffect(key1 = isUserSignIn) {
        if (isUserSignIn) {
            // keyboardController.hide()
            // navController.navigate(BottomNavItem.Profile.fullRoute)
            navController.navigate(ROUTE_DASHBOARD)
            Log.d("Auth", "Loged in")
        }
    }

    FitbitesmobileTheme(dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            AuthHeader(
                title = stringResource(R.string.welcome_back),
                subtitle = stringResource(R.string.sign_in_to_access_your_account)
            )

            Spacer(modifier = Modifier.height(32.dp))

            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                trailingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isPassword = true,
                isPasswordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.forgot_password),
                color = Color.Green,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onForgotPasswordClick() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            GradientButton(
                text = "Sign In",
                onClick = {
                    authViewModel.signIn(email, password)
                },
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            SocialMediaSection(
                onGoogleAuth = {
                    Log.d("SocialAuth", "Google icon clicked")
                },
                onFacebookAuth = {
                    Log.d("SocialAuth", "Facebook icon clicked")
                },
                onAppleAuth = {
                    Log.d("SocialAuth", "Apple icon clicked")
                },
                text = "or sign in with"
            )

            Spacer(modifier = Modifier.weight(1f))

            AuthFooter(
                message = stringResource(R.string.don_t_have_an_account),
                actionText = stringResource(R.string.sign_up),
                onActionClick = { onSignUpClick() }
            )

        }
    }
}




