package com.example.fitbites.presentation.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun WaterTrackerCard(
    currentWater: Float,
    goalWater: Float,
    lastUpdateTime: String,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
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
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "Water",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 14.sp,
                        )
                        Text(
                            text = String.format("%.2f / %.1f L", currentWater, goalWater),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround,
                                modifier = Modifier.padding(10.dp).height(80.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape)
                                        .padding(6.dp)
                                ) {
                                    IconButton(
                                        onClick = {onIncrease()},
                                        modifier = Modifier.fillMaxSize(),
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Increase",
                                                tint = MaterialTheme.colorScheme.onPrimary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape)
                                        .padding(6.dp)
                                ) {
                                    IconButton(
                                        onClick = {onDecrease()},
                                        modifier = Modifier.fillMaxSize(),
                                        content = {
                                            Icon(
                                                imageVector = Icons.Default.Remove,
                                                contentDescription = "Decrease",
                                                tint = MaterialTheme.colorScheme.onPrimary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(100.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.tertiaryContainer,
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight((currentWater / goalWater).coerceIn(0f, 1f))
                                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(Color(0xFF42A5F5), Color(0xFF80D8FF))
                                            )
                                        )
                                )

                                Text(
                                    text = "${((currentWater / goalWater) * 100).toInt()}%",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(4.dp)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AccessTime,
                        contentDescription = "history",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Last time $lastUpdateTime",
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
    }
}


//@Preview(name = "Light Mode", showBackground = true)
//@Composable
//fun WaterTrackerCardLightPreview() {
//    FitbitesmobileTheme(darkTheme = false, dynamicColor = false) {
//        WaterTrackerCard(
//            currentWater = 1.9f,
//            goalWater = 2.5f,
//            lastUpdateTime = "10:45 AM",
//            onIncrease = {},
//            onDecrease = {}
//        )
//    }
//}
//
//@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun WaterTrackerCardDarkPreview() {
//    FitbitesmobileTheme(darkTheme = true, dynamicColor = false) {
//        WaterTrackerCard(
//            currentWater = 1.9f,
//            goalWater = 2.5f,
//            lastUpdateTime = "10:45 AM",
//            onIncrease = {},
//            onDecrease = {}
//        )
//    }
//}