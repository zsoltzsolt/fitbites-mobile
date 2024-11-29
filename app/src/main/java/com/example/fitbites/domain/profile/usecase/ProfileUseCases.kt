package com.example.fitbites.domain.profile.usecase

data class ProfileUseCases(
    val fetchProfile: FetchProfile,
    val updateProfile: UpdateProfile,
    val deleteProfile: DeleteProfile
)
