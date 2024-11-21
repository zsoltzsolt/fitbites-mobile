package com.example.fitbites.auth.domain.usecase

import com.example.fitbites.auth.domain.repository.AuthRepository

class SignIn(
    private val authScreenRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = authScreenRepository.signIn(email, password)
}