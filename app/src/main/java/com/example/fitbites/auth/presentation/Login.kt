package com.example.fitbites.auth.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.graphics.Brush
import com.example.fitbites.R
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.fitbites.SplashScreen
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.ui.theme.ThemeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = screenWidth * 0.45f
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

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(iconSize.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Welcome Back",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.sign_in_to_access_your_account),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(
                        text = stringResource(R.string.enter_your_email),
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = stringResource(R.string.email_icon),
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    containerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = stringResource(R.string.password), color = Color.Gray) },
                trailingIcon = {
                    val icon =
                        if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(R.string.toggle_password_visibility),
                        tint = Color.Gray,
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    containerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
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

            Button(
                onClick = onSignInClick,
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF32CD32),
                                Color(0xFF228B22)
                            )
                        ),
                        shape = RoundedCornerShape(50)
                    )
            ) {
                Text(text = stringResource(R.string.sign_in), color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.or_sign_in_with),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google), // Replace with your Google icon resource
                    contentDescription = stringResource(R.string.google),
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )

                Icon(
                    painter = painterResource(id = R.drawable.facebook), // Replace with your Facebook icon resource
                    contentDescription = stringResource(R.string.facebook),
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )

                Icon(
                    painter = painterResource(id = R.drawable.apple), // Replace with your Apple icon resource
                    contentDescription = stringResource(R.string.apple),
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = stringResource(R.string.don_t_have_an_account),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(R.string.sign_up),
                    color = Color.Green,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { onSignUpClick() }
                        .fillMaxHeight()
                        .padding(bottom = (screenHeight * 0.05).dp)
                )
            }
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
fun PreviewSplashScreen() {
    LoginScreen(onSignInClick = {}, onForgotPasswordClick = {}, onSignUpClick = {})
}

