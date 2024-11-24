package com.example.fitbites.presentation.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Dashboard() {

    Box(Modifier.fillMaxSize()) {
        Text("HELLO", Modifier.align(Alignment.Center),
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            ))
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewDashboard() {
    Dashboard()
}
