package com.example.fitbites.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitbites.presentation.components.GradientButton
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun ConfigurableSelectionScreen(
    title: String,
    subtitle: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onNextClicked: () -> Unit
) {

    FitbitesmobileTheme(dynamicColor = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                ConfigurableHeader(
                    title = title,
                    subtitle = subtitle
                )

                GoalOptions(
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
            }

            GradientButton(
                text = "Next",
                onClick = onNextClicked,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 30.dp)
            )
        }
    }
}

@Composable
fun GoalOptions(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.forEach { option ->
            Button(
                onClick = { onOptionSelected(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical =4.dp, horizontal = 30.dp),
                border = BorderStroke(2.dp, if (selectedOption == option) Color(0xFF00FF00) else Color.Transparent),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(text = option, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Preview(
    name = "Light Mode 2",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode 2",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewConfigurableSelectionScreen() {
    val selectedOption = remember { mutableStateOf("Keep weight") }

    ConfigurableSelectionScreen(
        title = "What's your goal?",
        subtitle = "We will calculate daily calories according to your goal",
        options = listOf("Lose weight", "Keep weight", "Gain weight", "Other"),
        selectedOption = selectedOption.value,
        onOptionSelected = { selectedOption.value = it },
        onNextClicked = {
        }
    )
}
