package com.example.fitbites.presentation.chat

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    var messageText by remember { mutableStateOf("") }
    val chatMessages by viewModel.chatMessages.collectAsState()

    FitbitesmobileTheme(dynamicColor = false) {
        Column(modifier = Modifier.fillMaxSize()) {
            ChatHeader(
                onBackClick = { },
                botName = "FitBot",
                status = "Always active"
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(chatMessages) { message ->
                    when (message.sender) {
                        Sender.USER -> {
                            MessageBox(
                                text = message.content
                            )
                        }
                        Sender.ASSISTANT -> {
                            FullWidthMessage(content = message.content)
                        }
                    }
                }
            }

            MessageSenderBar(
                messageText = messageText,
                onMessageChange = { messageText = it },
                onSendClick = {
                    viewModel.sendMessage(messageText)
                    messageText = ""
                }
            )
        }
    }
}

@Composable
fun FullWidthMessage(content: String) {
    Text(
        text = content,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSecondary
        )
    )
}




