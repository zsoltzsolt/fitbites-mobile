package com.example.fitbites.presentation.camera

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.domain.nutrition.model.Ingredient
import com.example.fitbites.domain.nutrition.model.TotalMeal
import com.example.fitbites.domain.nutrition.usecase.NutritionUseCases
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraUseCases: NutritionUseCases
) : ViewModel() {
    private val _uploadStatus = MutableStateFlow("Uploading...")
    val uploadStatus = _uploadStatus.asStateFlow()

    private val _totalMeal = MutableStateFlow<TotalMeal?>(null)
    val totalMeal = _totalMeal.asStateFlow()

    private val _ingredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredients = _ingredients.asStateFlow()

    fun deleteUploadStatus() {
        _uploadStatus.value = ""
    }

    fun uploadPhoto(photoFile: File, context: Context) {
        Log.d("UploadPhoto", "Uploading file: ${photoFile.name}")
        val mediaType = "image/jpeg".toMediaType()
        val requestFile = photoFile.asRequestBody(mediaType)
        val body = MultipartBody.Part.createFormData("file", photoFile.name, requestFile)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                cameraUseCases.uploadImage(body).collect { response ->
                    Log.d("UploadResponse", "Response received: $response")
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            _totalMeal.value = apiResponse.total_meal
                            _ingredients.value = apiResponse.ingredients.values.toList()
                            Log.d("UploadSuccess", "Meal: ${_totalMeal.value}, Ingredients: ${_ingredients.value}")
                            _uploadStatus.value = "Upload successful"
                        } else {
                            _uploadStatus.value = "Empty response from server"
                        }
                    } else {
                        _uploadStatus.value = "Upload failed: ${response.errorBody()?.string()}"
                    }
                }
            } catch (e: Exception) {
                Log.e("UploadError", "Failed to upload photo", e)
                _uploadStatus.value = "Upload failed"
            }
        }
    }

    fun saveMealWithIngredients(totalMeal: TotalMeal, ingredients: List<Ingredient>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cameraUseCases.saveMeal(totalMeal, ingredients).collect { response ->
                    when (response) {
                        is Response.Success -> {
                            Log.d("SaveMeal", "Meal and ingredients saved successfully.")
                        }
                        is Response.Error -> {
                            Log.e("SaveMeal", "Error saving meal: ${response.message}")
                        }

                        Response.Loading -> {

                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("SaveMeal", "Exception occurred: $e")
            }
        }
    }


}

