package com.example.fitbites.domain.profile.usecase

import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.domain.profile.repository.ProfileRepository
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

class UpdateProfile(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userProfile: UserProfile): Flow<Response<Boolean>> = repository.updateProfile(userProfile)
}