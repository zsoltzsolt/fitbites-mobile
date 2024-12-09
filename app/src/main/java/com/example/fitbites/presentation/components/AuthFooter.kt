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
import androidx.compose.ui.tooling.preview.Preview
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
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 13.sp,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = actionText,
            color = Color(0xFF16DB16),
            fontSize = 13.sp,
            modifier = Modifier.clickable { onActionClick() }
        )
    }
}

@Preview
@Composable
private fun AUthFooterPreview() {
    AuthFooter("Already have an account? ", "Sign in", {})
}
