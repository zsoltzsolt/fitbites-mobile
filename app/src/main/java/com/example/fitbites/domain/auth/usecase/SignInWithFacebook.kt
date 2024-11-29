package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class SignInWithFacebook(private val authRepository: AuthRepository) {
    suspend operator fun invoke(accessToken: String) = authRepository.signInWithFacebook(accessToken)
}