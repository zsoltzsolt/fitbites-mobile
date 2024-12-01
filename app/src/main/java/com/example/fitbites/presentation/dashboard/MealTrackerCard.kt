package com.example.fitbites.presentation.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.R
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import com.example.fitbites.ui.theme.ThemeColors
import java.nio.file.WatchEvent

@Composable
fun MealTimeRow(
    iconResId: Int,
    label: String,
    time: String,
    calories: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, 
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                modifier = Modifier.size(35.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AccessTime,
                        contentDescription = "history",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                    )
                    Text(
                        text = time,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }

        Text(
            text = "${calories.toInt()} kcal",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
fun MealTrackerCard(
    currentBreakfastCalories: Float,
    currentLunchCalories: Float,
    currentDinnerCalories: Float,
    currentSnacksCalories: Float,
    breakfastTime: String,
    lunchTime: String,
    dinnerTime: String,
    snacksTime: String
) {
    CompositionLocalProvider(LocalTonalElevationEnabled provides false) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    MealTimeRow(
                        iconResId = R.drawable.breakfast,
                        label = "Breakfast",
                        time = breakfastTime,
                        currentBreakfastCalories
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Divider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(9.dp))
                    MealTimeRow(
                        iconResId = R.drawable.lunch,
                        label = "Lunch",
                        time = lunchTime,
                        currentLunchCalories
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Divider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(9.dp))
                    MealTimeRow(
                        iconResId = R.drawable.dinner,
                        label = "Dinner",
                        time = dinnerTime,
                        currentDinnerCalories
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Divider(color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.05f), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(9.dp))
                    MealTimeRow(
                        iconResId = R.drawable.snacks,
                        label = "Snacks",
                        time = snacksTime,
                        currentSnacksCalories
                    )
                }
            }
        }
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Composable
fun MealTrackerCardLightPreview() {
    FitbitesmobileTheme(darkTheme = false, dynamicColor = false) {
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

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MealTrackerCardDarkPreview() {
    FitbitesmobileTheme(darkTheme = true, dynamicColor = false) {
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
