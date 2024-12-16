package com.example.fitbites.presentation.nutritionBreakdown

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitbites.domain.api.model.Ingredient
import com.example.fitbites.domain.api.model.TotalMeal
import com.example.fitbites.presentation.camera.CameraViewModel
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.fitbites.navigation.ROUTE_ANALYSIS
import kotlinx.coroutines.flow.collectLatest


@Composable
fun FoodAnalysisScreen() {
    val viewModel: CameraViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
    val totalMeal by viewModel.totalMeal.collectAsState()
    val ingredients by viewModel.ingredients.collectAsState()

    LaunchedEffect(totalMeal, ingredients) {
        Log.d("FoodAnalysisScreen", "Meal: $totalMeal, Ingredients: $ingredients")
        viewModel.uploadStatus.collectLatest { totalMeal ->
            Log.d("FoodAnalysisScreen", "$totalMeal")
        }
    }


    when {
        totalMeal == null -> Text("Loading meal data...")
        ingredients.isEmpty() -> Text("No ingredients found.")
        else -> FoodAnalysisContent(totalMeal!!, ingredients)
    }
}




@Composable
fun FoodAnalysisContent(summary: TotalMeal, ingredients: List<Ingredient>) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Food Analysis",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    color = MaterialTheme.colorScheme.onPrimary,
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

