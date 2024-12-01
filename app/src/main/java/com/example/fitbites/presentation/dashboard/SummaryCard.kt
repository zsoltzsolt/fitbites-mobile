package com.example.fitbites.presentation.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun SummaryCard(
    proteins: Pair<Int, Int>,
    fats: Pair<Int, Int>,
    carbs: Pair<Int, Int>,
    calories: Pair<Int, Int>
) {
    CompositionLocalProvider(LocalTonalElevationEnabled provides false) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 5.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf(
                        "Proteins" to proteins,
                        "Fats" to fats,
                        "Carbs" to carbs
                    ).forEach { (label, value) ->
                        NutrientProgress(
                            label = label,
                            current = value.first,
                            goal = value.second,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                NutrientProgress(
                    label = "Calories",
                    current = calories.first,
                    goal = calories.second,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun NutrientProgress(label: String, current: Int, goal: Int, modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$current / $goal",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
        LinearProgressIndicator(
            progress = (current.toFloat() / goal),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = when (label) {
                "Proteins" -> Color.Red
                "Fats" -> Color(0xFFFFD700)
                "Carbs" -> Color(0xFF32CD32)
                else -> Color(0xFF5DADE2)
            },
            trackColor = MaterialTheme.colorScheme.tertiaryContainer
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = label, color = MaterialTheme.colorScheme.onPrimary, fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }

}


@Preview(name = "Light Mode", showBackground = true)
@Composable
fun SummaryCardLightPreview() {
    FitbitesmobileTheme(darkTheme = false, dynamicColor = false) {
        SummaryCard(
            proteins = 150 to 225,
            fats = 30 to 118,
            carbs = 319 to 340,
            calories = 2456 to 3400
        )
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SummaryCardDarkPreview() {
    FitbitesmobileTheme(darkTheme = true, dynamicColor = false) {
        SummaryCard(
            proteins = 150 to 225,
            fats = 30 to 118,
            carbs = 319 to 340,
            calories = 2456 to 3400
        )
    }
}
