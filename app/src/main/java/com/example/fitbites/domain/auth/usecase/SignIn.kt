package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class SignIn(
    private val authScreenRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = authScreenRepository.signIn(email, password)
}