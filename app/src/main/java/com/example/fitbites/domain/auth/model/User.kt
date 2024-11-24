package com.example.fitbites.domain.auth.model

import java.util.UUID

data class User(
    var profileUUID: String = "",
    var userEmail: String = "",
    var userName: String = "",
    var userProfilePictureUrl: String = ""
)
