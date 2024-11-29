package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class SignOut(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.signOut()
}