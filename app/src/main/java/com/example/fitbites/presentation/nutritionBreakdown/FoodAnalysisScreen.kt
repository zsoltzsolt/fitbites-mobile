package com.example.fitbites.presentation.nutritionBreakdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitbites.domain.api.model.Ingredient
import com.example.fitbites.domain.api.model.TotalMeal
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun FoodAnalysisScreen(summary: TotalMeal, ingredients: List<Ingredient>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SummaryBox(summary)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            ) {
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            ingredients.forEach { ingredient ->
                IngredientItem(ingredient)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        GradientButton(
            "Track meal",
            {},
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun PreviewFoodAnalysisScreen() {
    FitbitesmobileTheme(darkTheme = false, dynamicColor = false) {
        val summary = TotalMeal(
            Calories = 247.5,
            Proteins = 47.5,
            Carbs = 12.0,
            Fats = 25.0
        )

        val ingredients = listOf(
            Ingredient(247.5, 12.5, 15.0, 17.5, 30),
            Ingredient(247.5, 12.5, 10.0, 17.5, 30),
            Ingredient(247.5, 12.5, 2.0, 17.5, 75)
        )

        FoodAnalysisScreen(summary, ingredients)
    }
}

@Preview(name = "Dark Mode", showBackground = true)
@Composable
private fun PreviewFoodAnalysisScreenDark() {
    FitbitesmobileTheme(darkTheme = true, dynamicColor = false) {
        val summary = TotalMeal(
            Calories = 247.5,
            Proteins = 47.5,
            Carbs = 12.0,
            Fats = 25.0
        )

        val ingredients = listOf(
            Ingredient(247.5, 12.5, 15.0, 17.5, 30),
            Ingredient(247.5, 12.5, 10.0, 17.5, 30),
            Ingredient(247.5, 12.5, 2.0, 17.5, 75)
        )

        FoodAnalysisScreen(summary, ingredients)
    }
}
