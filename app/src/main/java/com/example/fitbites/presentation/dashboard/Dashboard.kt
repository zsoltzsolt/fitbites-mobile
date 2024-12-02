package com.example.fitbites.presentation.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitbites.navigation.ROUTE_DASHBOARD
import com.example.fitbites.navigation.ROUTE_LOGIN
import com.example.fitbites.presentation.auth.AuthViewModel
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun Dashboard(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 26.dp, horizontal = 6.dp)

    ) {
        FitbitesmobileTheme(dynamicColor = false) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Summary",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            SummaryCard(
                proteins = 150 to 225,
                fats = 30 to 118,
                carbs = 319 to 340,
                calories = 2456 to 3400
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Water Intake",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            WaterTrackerCard(
                1.9f,
                2.5f,
                "10:45AM",
                {},
                {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ){
                Text(
                    text = "Meals",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
                IconButton(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    onClick = { },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )
            }
            MealTrackerCard(
                currentBreakfastCalories = 300f,
                currentLunchCalories = 450f,
                currentDinnerCalories = 600f,
                currentSnacksCalories = 150f,
                breakfastTime = "8:00 AM",
                lunchTime = "12:00 PM",
                dinnerTime = "7:00 PM",
                snacksTime = "4:00 PM"
            )
        }
    }
}
