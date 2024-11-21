package com.example.fitbites.auth.domain.model

import java.util.UUID

data class User(
    var profileUUID: String = "",
    var userEmail: String = "",
    var userName: String = "",
    var userProfilePictureUrl: String = ""
)
