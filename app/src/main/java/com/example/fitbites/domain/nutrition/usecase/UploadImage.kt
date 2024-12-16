package com.example.fitbites.domain.nutrition.usecase

import com.example.fitbites.domain.nutrition.model.ApiResponse
import com.example.fitbites.repository.nutrition.NutritionRepositoryImpl
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class UploadImage @Inject constructor(
    private val repository: NutritionRepositoryImpl
) {
    suspend operator fun invoke(image: MultipartBody.Part): Flow<Response<ApiResponse>> {
        return repository.uploadImage(image)
    }
}
