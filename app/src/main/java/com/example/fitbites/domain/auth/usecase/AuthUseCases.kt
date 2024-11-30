package com.example.fitbites.domain.auth.usecase

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val isSetupCompleted: IsSetupCompleted,
    val signIn: SignIn,
    val signUp: SignUp,
    val signUpWithGoogle: SignUpWithGoogle,
    val signInWithGoogle: SignInWithGoogle,
    val signInWithFacebook: SignInWithFacebook,
    val signUpWithFacebook: SignUpWithFacebook,
    val signOut: SignOut,
    val sendPasswordResetEmail: SendPasswordResetEmail,
    val sendEmailVerification: SendEmailVerification
)
