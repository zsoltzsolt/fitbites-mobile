package com.example.fitbites.auth.domain.repository

import arrow.core.Either
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Flow<Response<Boolean>>
    suspend fun signIn(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<Response<Boolean>>

}