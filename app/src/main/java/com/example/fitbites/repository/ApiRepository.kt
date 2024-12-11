package com.example.fitbites.repository


import com.example.fitbites.domain.api.model.ApiResponse
import com.example.fitbites.network.RetrofitInstance
import okhttp3.MultipartBody
import retrofit2.Response

class ApiRepository {
    suspend fun uploadImage(image: MultipartBody.Part): Response<ApiResponse> {
        return RetrofitInstance.api.uploadImage(image)
    }
}
