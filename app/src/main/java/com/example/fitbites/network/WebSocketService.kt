package com.example.fitbites.network

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface WebSocketService {
    @Send
    fun sendMessage(message: String)

    @Receive
    fun receiveMessages(): Flowable<String>

    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>
}

