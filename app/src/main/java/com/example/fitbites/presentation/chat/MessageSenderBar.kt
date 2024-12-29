package com.example.fitbites.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitbites.ui.theme.FitbitesmobileTheme

@Composable
fun MessageSenderBar(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = messageText,
            onValueChange = onMessageChange,
            placeholder = { Text("Type a message...") },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        )
        IconButton(onClick = onSendClick) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Heart",
                tint = Color.Green
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMessageBar() {
    FitbitesmobileTheme(dynamicColor = false) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            MessageSenderBar("", {}, {})
        }
    }

}
