package com.example.fitbites.auth.domain.usecase

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signIn: SignIn,
    val signUp: SignUp
)
