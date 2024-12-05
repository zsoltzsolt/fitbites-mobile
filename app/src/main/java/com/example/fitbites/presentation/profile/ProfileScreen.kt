package com.example.fitbites.presentation.profile

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.R
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.navigation.ROUTE_LOGIN
import com.example.fitbites.presentation.auth.AuthViewModel
import com.example.fitbites.presentation.splash.SplashScreen
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.utils.Response

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {
    var isDarkTheme by remember { mutableStateOf(true) }
    val userProfileState by profileViewModel.userProfileState.collectAsState()

    FitbitesmobileTheme(dynamicColor = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Profile",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            when (userProfileState) {
                is Response.Loading -> {
                }
                is Response.Success -> {
                    val userProfile = (userProfileState as Response.Success<UserProfile>).data
                    Text(
                        text = userProfile.name,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                is Response.Error -> {
                    Text(
                        text = "Error",
                        color = Color.Red,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            Text(
                text = "example@mail.com",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard(label = "Calorie Intake", value = "3400 kCal")
            ProfileCard(label = "Weight Unit", value = "Kilograms")

            Spacer(modifier = Modifier.height(24.dp))

            Divider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f), thickness = 1.dp)

            CompositionLocalProvider(LocalTonalElevationEnabled provides false) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    elevation = CardDefaults.elevatedCardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Dark mode",
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                                fontSize = 14.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = { isDarkTheme = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.background,
                                    uncheckedThumbColor = Color.Gray,
                                    checkedTrackColor = Color.Green
                                ),
                                modifier = Modifier.scale(0.65f)
                            )
                        }

                        Divider(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f),
                            thickness = 1.dp
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Mail,
                            label = "Contact us",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                            onClick = {}
                        )

                        Divider(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f),
                            thickness = 1.dp
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Info,
                            label = "About app",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                            onClick = {}
                        )

                        Divider(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f),
                            thickness = 1.dp
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Settings,
                            label = "Settings",
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.75f),
                            onClick = {}
                        )

                        Divider(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f),
                            thickness = 1.dp
                        )

                        ProfileOptionItem(
                            icon = Icons.Default.Logout,
                            label = "Logout",
                            color = Color.Red,
                            onClick = {
                                authViewModel.signOut()
                                navController.navigate(ROUTE_LOGIN)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }

        }
    }
}



@Composable
fun ProfileCard(label: String, value: String) {
    CompositionLocalProvider(LocalTonalElevationEnabled provides false) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun ProfileOptionItem(
    icon: ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() }, // Make the entire row clickable
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = color
        )
        Text(
            text = label,
            color = color,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}



