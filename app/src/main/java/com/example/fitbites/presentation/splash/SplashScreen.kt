package com.example.fitbites.presentation.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitbites.R
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = screenWidth * 0.65f
    val verticalOffset = screenHeight * 0.1f

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
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
                style = MaterialTheme.typography.headlineSmall,
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
    SplashScreen({})
}

