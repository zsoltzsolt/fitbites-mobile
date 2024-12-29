package com.example.fitbites.network

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient

class WebSocketClient {
    private val okHttpClient = OkHttpClient()

    fun createService(): WebSocketService {
        return Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory("ws://192.168.1.2:8000/ws"))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .build()
            .create(WebSocketService::class.java)
    }
}
