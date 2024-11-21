package com.example.fitbites.auth.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.auth.domain.repository.AuthRepository
import com.example.fitbites.auth.domain.usecase.AuthUseCases
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var isUserAuthenticatedState = mutableStateOf(false)
        private set
    var isUserSignInState = mutableStateOf(false)
        private set
    var isUserSignUpState = mutableStateOf(false)
        private set

    init {
        isUserAuthenticated()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.signIn(email, password).collect { response ->
                when (response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        isUserSignInState.value = response.data
                        // toastMessage.value = "Login Successful"
                    }
                    is Response.Error -> {
                        // toastMessage.value = "Login Failed"
                    }
                }
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.signUp(email, password).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        //toastMessage.value = ""
                    }
                    is Response.Success -> {
                        isUserSignUpState.value = response.data
                        //toastMessage.value = "Sign Up Successful"
                        Log.d("TAG", "Sign Up Successful")
                        //firstTimeCreateProfileToFirebase()
                    }
                    is Response.Error -> {
                        try {
                            //toastMessage.value = "Sign Up Failed"
                            Log.d("TAG", "Sign Up Failed")
                        }catch (e: Exception){
                            Log.e("TAG", "signUp: ", Throwable(e))
                        }
//                        Timber.tag("TAG").e("signUp: ")
                    }
                }
            }
        }
    }

    private fun isUserAuthenticated() {
        viewModelScope.launch {
            authUseCases.isUserAuthenticated().collect { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Success -> {
                        isUserAuthenticatedState.value = response.data
                    }
                    is Response.Error -> {}
                }
            }
        }
    }



}