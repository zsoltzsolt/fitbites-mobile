package com.example.fitbites.auth.domain.usecase

import com.example.fitbites.auth.domain.repository.AuthRepository

class SignUp(
    private val authScreenRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = authScreenRepository.signUp(email, password)
}