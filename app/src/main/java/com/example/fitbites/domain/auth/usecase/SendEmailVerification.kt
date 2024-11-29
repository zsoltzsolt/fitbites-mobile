package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

class SendEmailVerification (
  private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Flow<Response<Boolean>> {
        return authRepository.sendEmailVerification()
    }
}