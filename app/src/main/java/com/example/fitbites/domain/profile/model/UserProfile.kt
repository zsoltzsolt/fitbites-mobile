package com.example.fitbites.domain.profile.model

data class UserProfile(
    val name: String = "",
    val setupComplete: Boolean = false,
    var goal: String = "",
    var gender: String = "",
    var activityLevel: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var age: Int = 0
)
