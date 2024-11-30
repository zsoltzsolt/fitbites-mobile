package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class SignUp(
    private val authScreenRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, username: String) = authScreenRepository.signUp(email, password, username)
}