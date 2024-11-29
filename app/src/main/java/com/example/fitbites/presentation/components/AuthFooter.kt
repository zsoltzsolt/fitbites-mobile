package com.example.fitbites.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthFooter(
    message: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = actionText,
            color = Color.Green,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onActionClick() }
        )
    }
}
