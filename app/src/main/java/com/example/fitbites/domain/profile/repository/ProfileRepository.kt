package com.example.fitbites.domain.profile.repository

import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun fetchProfile(): Flow<Response<UserProfile>>
    suspend fun updateProfile(userProfile: UserProfile): Flow<Response<Boolean>>
    suspend fun deleteProfile(): Flow<Response<Boolean>>
}