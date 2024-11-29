package com.example.fitbites.domain.profile.usecase

import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.domain.profile.repository.ProfileRepository
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

class FetchProfile(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Flow<Response<UserProfile>> = repository.fetchProfile()
}