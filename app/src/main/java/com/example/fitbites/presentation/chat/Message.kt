package com.example.fitbites.presentation.chat

data class Message(
    val content: String,
    val sender: Sender
)

enum class Sender {
    USER,
    ASSISTANT
}

