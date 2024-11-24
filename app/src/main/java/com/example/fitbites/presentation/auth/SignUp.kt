package com.example.fitbites.presentation.auth

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = screenWidth * 0.45f

    val isUserSignUp = authViewModel.isUserSignUpState.value
    LaunchedEffect(key1 = isUserSignUp) {
        if (isUserSignUp) {
            Log.e("Auth", "Crated account")
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

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(iconSize.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Get Started",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "by creating a free account.",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(
                        text = "Full name",
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Person,
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
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(10.dp),
                label = {
                    Text(
                        text = "Email",
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
                label = { Text(text = "Password", color = Color.Gray) },
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

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 5.dp)
                        .size(16.dp)
                        .clickable { checked = !checked }
                ) {
                    Icon(
                        imageVector = if (checked) Icons.Filled.CheckBox else Icons.Filled.CheckBoxOutlineBlank,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }


                Text(
                    text = "Agree to our",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = " Terms",
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = " and",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = " Conditions",
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    authViewModel.signUp(email!!, password!!)
                },
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
                Text(text = "Sign Up", color = Color.White, fontSize = 18.sp)
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
                    text = "or sign up with",
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
                    text = "Already a member? ",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp
                )
                Text(
                    text = "Sign in",
                    color = Color.Green,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .clickable { onSignInClick() }
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
fun PreviewSignUp() {
    SignUpScreen(onSignUpClick = {}, onSignInClick = {})
}

