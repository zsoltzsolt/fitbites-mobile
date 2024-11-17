package com.example.fitbites

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.auth.presentation.LoginScreen
import com.example.fitbites.auth.presentation.SignUpScreen
import com.example.fitbites.ui.theme.ThemeColors


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitbitesmobileTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen()
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000)
            showSplash = false
        }
    } else {
        SignUpScreen(onSignInClick = {}, onForgotPasswordClick = {}, onSignUpClick = {})
    }
}

@Composable
fun SplashScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = screenWidth * 0.65f
    val verticalOffset = screenHeight * 0.1f

    FitbitesmobileTheme(dynamicColor = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize.dp)
                    .offset(y = -verticalOffset.dp)
            )

            Text(
                text = "FitBites",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -verticalOffset.dp)
            )
            Text(
                text = "Version 1.0",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.headlineSmall ,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -verticalOffset.dp + 20.dp)
            )
        }
    }
}



@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewSplashScreenDark() {
    SplashScreen()
}


