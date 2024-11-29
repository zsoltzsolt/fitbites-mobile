package com.example.fitbites.domain.auth.usecase

import com.example.fitbites.domain.auth.repository.AuthRepository

class IsUserAuthenticated(
    private val authScreenRepository: AuthRepository
) {
    operator fun invoke() = authScreenRepository.isUserAuthenticatedInFirebase()
}