package com.example.fitbites.domain.api.usecase

import com.example.fitbites.domain.api.model.ApiResponse
import com.example.fitbites.repository.ApiRepository
import okhttp3.MultipartBody
import retrofit2.Response

class UploadImage(private val repository: ApiRepository) {

    suspend fun uploadImage(image: MultipartBody.Part): Response<ApiResponse> {
        return repository.uploadImage(image)
    }
}