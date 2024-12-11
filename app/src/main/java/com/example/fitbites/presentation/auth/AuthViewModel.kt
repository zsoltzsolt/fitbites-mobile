package com.example.fitbites.presentation.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitbites.domain.auth.usecase.AuthUseCases
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    private val _passwordResetState = MutableStateFlow<Response<Boolean>?>(null)
    val passwordResetState: StateFlow<Response<Boolean>?> get() = _passwordResetState
    private val _emailVerificationState = MutableStateFlow<Response<Boolean>?>(null)
    val emailVerificationState: StateFlow<Response<Boolean>?> get() = _emailVerificationState

    var isUserAuthenticatedState = mutableStateOf(false)
        private set

    var isSetupComplete = mutableStateOf(false)
        private set


    init {
        isUserAuthenticated()
        isSetupCompleted()
    }

    fun sendEmailVerification() {
        viewModelScope.launch {
            authUseCases.sendEmailVerification().collect { response ->
                _emailVerificationState.value = response
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            authUseCases.signInWithGoogle(idToken).collect { response ->
                when (response) {
                    is Response.Loading -> {  }
                    is Response.Success -> {
                        isUserAuthenticatedState.value = response.data
                        sendEmailVerification()
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }


    fun signIn(email: String, password: String, context: Context) {
        viewModelScope.launch {
            authUseCases.signIn(email, password).collect { response ->
                when (response) {
                    is Response.Loading -> {
                    }

                    is Response.Success -> {
                        isUserAuthenticatedState.value = response.data
                        isUserAuthenticated()
                        isSetupCompleted()
                    }

                    is Response.Error -> {
                        Toast.makeText(context, "Invalid email or password.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun signUpWithGoogle(idToken: String) {
        viewModelScope.launch {
            authUseCases.signUpWithGoogle(idToken).collect { response: Response<Boolean> ->
                when (response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }

    fun signUp(email: String, password: String, username: String, context: Context) {
        viewModelScope.launch {
            authUseCases.signUp(email, password, username).collect { response ->
                when (response) {
                    is Response.Loading -> {
                    }
                    is Response.Success -> {
                        Log.d("TAG", "Sign Up Successful")
                        Toast.makeText(context, "Sign-up complete! You can now log in with your credentials.", Toast.LENGTH_SHORT).show()
                    }
                    is Response.Error -> {
                        try {
                            Log.d("TAG", "Sign Up Failed: ${response.message}")
                            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                        }catch (e: Exception){
                            Log.e("TAG", "signUp: ", Throwable(e))
                        }
                    }
                }
            }
        }
    }

    fun signInWithFacebook(accessToken: String) {
        viewModelScope.launch {
            authUseCases.signInWithFacebook(accessToken).collect { response ->
                when (response) {
                    is Response.Loading -> { }
                    is Response.Success -> {
                        isUserAuthenticatedState.value = response.data
                    }
                    is Response.Error -> {
                    }
                }
            }
        }
    }

    fun signUpWithFacebook(accessToken: String) {
        viewModelScope.launch {
            authUseCases.signUpWithFacebook(accessToken).collect { response ->
                when (response) {
                    is Response.Loading -> { }
                    is Response.Success -> {
                        isUserAuthenticatedState.value = response.data
                    }
                    is Response.Error -> {
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

     fun isSetupCompleted() {
        viewModelScope.launch {
            authUseCases.isSetupCompleted().collect() { response ->
                when (response) {
                    is Response.Loading -> {}
                    is Response.Success -> {
                        Log.d("USER_PROFILE", response.toString())
                        isSetupComplete.value = response.data
                    }
                    is Response.Error -> {}
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authUseCases.signOut().collect() { response ->
                when(response) {
                    is Response.Loading -> {

                    }
                    is Response.Success -> {
                        isUserAuthenticatedState.value = false
                    }
                    is Response.Error -> {

                    }
                }

            }
        }
    }

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            authUseCases.sendPasswordResetEmail(email).collect { response ->
                _passwordResetState.value = response
                Log.d("Auth - PR", response.toString())
            }
        }
    }

}