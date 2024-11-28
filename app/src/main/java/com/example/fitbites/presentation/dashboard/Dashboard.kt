package com.example.fitbites.presentation.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitbites.navigation.ROUTE_DASHBOARD
import com.example.fitbites.navigation.ROUTE_LOGIN
import com.example.fitbites.presentation.auth.AuthViewModel
import com.example.fitbites.presentation.components.GradientButton

@Composable
fun Dashboard(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    Box(Modifier.fillMaxSize()) {
        Text("HELLO", Modifier.align(Alignment.Center),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ))
        GradientButton(
            text = "Log out",
            onClick = {
                authViewModel.signOut()
                navController.navigate(ROUTE_LOGIN)
            },
            modifier = Modifier.padding(horizontal = 30.dp).align(Alignment.Center)
        )
    }
}

