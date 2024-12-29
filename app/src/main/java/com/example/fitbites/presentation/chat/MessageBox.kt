package com.example.fitbites.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun MessageBox(
    text: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .align(Alignment.End)
        ) {
            BasicText(
                text = text,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(8.dp)
            )
        }
    }
}

