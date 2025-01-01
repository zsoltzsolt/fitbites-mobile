package com.example.fitbites.presentation.chat

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun FullWidthMessage(content: String) {
    Text(
        text = content,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}