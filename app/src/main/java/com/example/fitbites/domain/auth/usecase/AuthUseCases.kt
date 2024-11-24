package com.example.fitbites.domain.auth.usecase

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signIn: SignIn,
    val signUp: SignUp
)
