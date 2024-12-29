package com.example.fitbites.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fitbites.network.WebSocketClient
import com.tinder.scarlet.WebSocket
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel : ViewModel() {
    private val webSocketClient = WebSocketClient().createService()
    private val disposables = CompositeDisposable()

    private val _chatMessages = MutableStateFlow<List<Message>>(emptyList())
    val chatMessages: StateFlow<List<Message>> = _chatMessages

    init {
        connectWebSocket()
    }


    private val messageMap = mutableMapOf<String, Message>()

    private fun connectWebSocket() {
        disposables.add(
            webSocketClient.observeWebSocketEvent()
                .subscribeBy(
                    onNext = { event ->
                        when (event) {
                            is WebSocket.Event.OnMessageReceived -> {
                                val message = event.message.toString()

                                val parts = message.split(" ", limit = 2)
                                if (parts.size == 2) {
                                    val uniqueId = parts[0]
                                    val content = parts[1]
                                    messageMap[uniqueId] = Message(content, Sender.ASSISTANT)
                                    _chatMessages.update { messageMap.values.toList() }
                                }
                            }

                            is WebSocket.Event.OnConnectionClosed -> {}
                            is WebSocket.Event.OnConnectionClosing -> {}
                            is WebSocket.Event.OnConnectionFailed -> {}
                            is WebSocket.Event.OnConnectionOpened<*> -> {}
                        }
                    },
                    onError = { error ->
                        Log.e("WebSocket", "Error observing WebSocket events: $error")
                    }
                )
        )
    }




    fun sendMessage(message: String) {
        if (message.isNotBlank()) {
            val uniqueId = UUID.randomUUID().toString()
            Log.d("Websocket", "Sent: $message")
            webSocketClient.sendMessage(message)
            messageMap[uniqueId] = Message(message, Sender.USER)
            _chatMessages.update { messageMap.values.toList() }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
