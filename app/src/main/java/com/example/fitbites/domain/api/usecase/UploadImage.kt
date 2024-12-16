package com.example.fitbites.domain.api.usecase

import com.example.fitbites.domain.api.model.ApiResponse
import com.example.fitbites.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class UploadImage @Inject constructor(
    private val repository: ApiRepository
) {
    suspend operator fun invoke(image: MultipartBody.Part): Flow<Response<ApiResponse>> {
        return repository.uploadImage(image)
    }
}
