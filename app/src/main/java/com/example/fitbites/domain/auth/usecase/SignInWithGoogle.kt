package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class SignInWithGoogle(
    private val authScreenRepository: AuthRepository
) {
    suspend operator fun invoke(idToken: String) = authScreenRepository.signInWithGoogle(idToken)
}