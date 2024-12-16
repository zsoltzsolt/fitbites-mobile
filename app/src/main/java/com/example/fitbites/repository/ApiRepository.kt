package com.example.fitbites.repository


import com.example.fitbites.domain.api.model.ApiResponse
import com.example.fitbites.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.Response

class ApiRepository {
    suspend fun uploadImage(image: MultipartBody.Part): Flow<Response<ApiResponse>> = flow {
        val response = RetrofitInstance.api.uploadImage(image)
        emit(response)
    }
}

