package com.example.fitbites.auth.domain.usecase

import com.example.fitbites.auth.domain.repository.AuthRepository

class IsUserAuthenticated(
    private val authScreenRepository: AuthRepository
) {
    operator fun invoke() = authScreenRepository.isUserAuthenticatedInFirebase()
}