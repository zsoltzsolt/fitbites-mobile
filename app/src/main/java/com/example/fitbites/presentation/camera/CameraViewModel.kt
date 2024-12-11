package com.example.fitbites.presentation.camera

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody

class CameraViewModel : ViewModel() {

    var uploadStatus = mutableStateOf("")

    fun uploadPhoto(photoFile: File, context: Context) {
        val mediaType = "image/jpeg".toMediaType()
        val requestFile = photoFile.asRequestBody(mediaType)
        val body = MultipartBody.Part.createFormData("file", photoFile.name, requestFile)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.uploadImage(body)
                if (response.isSuccessful) {
                    Log.d("UploadResponse", "Upload successful: ${response.body()?.toString()}")
                    uploadStatus.value = "Upload successful"
                } else {
                    Log.d("UploadResponse", "Upload failed: ${response.errorBody()?.string()}")
                    uploadStatus.value = "Upload failed"
                }
            } catch (e: Exception) {
                Log.e("UploadError", "Failed to upload photo", e)
                uploadStatus.value = "Upload failed"
            }
        }
    }
}