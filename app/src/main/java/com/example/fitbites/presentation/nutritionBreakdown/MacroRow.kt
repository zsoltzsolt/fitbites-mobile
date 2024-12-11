package com.example.fitbites.presentation.nutritionBreakdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MacroRow(protein: Double, carbs: Double, fats: Double) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Color(0x330062FF))
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "P: ${protein}g",
                color = Color(0xFF0062FF),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 12.sp
            )
        }
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Color(0x33E4871C))
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "C: ${carbs}g",
                color = Color(0xFFE4871C),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 12.sp
            )
        }
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Color(0x333DA60D))
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "F: ${fats}g",
                color = Color(0xFF3DA60D),
                modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 12.sp
            )
        }
    }
}
