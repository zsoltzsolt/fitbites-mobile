package com.example.fitbites.domain.auth.repository

import arrow.core.Either
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Flow<Response<Boolean>>
    suspend fun signIn(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signUpWithGoogle(idToken: String): Flow<Response<Boolean>>
    suspend fun signInWithGoogle(idToken: String): Flow<Response<Boolean>>
}