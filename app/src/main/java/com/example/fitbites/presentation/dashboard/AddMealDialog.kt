package com.example.fitbites.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddMealDialog(onDismiss: () -> Unit, onOptionSelected: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Meal") },
        text = {
            Column {
                Text("Select an option:")
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    TextButton(onClick = { onOptionSelected("camera") }) {
                        Icon(Icons.Default.PhotoCamera, contentDescription = "Camera")
                        Text("  Camera")
                    }
                    TextButton(onClick = { onOptionSelected("microphone") }) {
                        Icon(Icons.Default.Mic, contentDescription = "Microphone")
                        Text("  Microphone")
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
