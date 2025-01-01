package com.example.fitbites.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitbites.ui.theme.FitbitesmobileTheme
import androidx.compose.foundation.lazy.items
import com.example.fitbites.presentation.chat.model.Sender


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
                },
                Modifier
            )
        }
    }
}





