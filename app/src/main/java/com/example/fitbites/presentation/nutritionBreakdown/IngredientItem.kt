package com.example.fitbites.presentation.nutritionBreakdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTonalElevationEnabled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.domain.nutrition.model.Ingredient

@Composable
fun IngredientItem(ingredient: Ingredient) {
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
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${ingredient.name}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Calories: ${ingredient.Calories} kcal",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    MacroRow(ingredient.Proteins, ingredient.Carbs, ingredient.Fats)
                }
                Text(
                    text = "${ingredient.grams} g",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}
