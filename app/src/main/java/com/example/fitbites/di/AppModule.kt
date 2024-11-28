package com.example.fitbites.di

import com.example.fitbites.repository.auth.AuthRepositoryImpl
import com.example.fitbites.domain.auth.repository.AuthRepository
import com.example.fitbites.domain.auth.usecase.AuthUseCases
import com.example.fitbites.domain.auth.usecase.IsUserAuthenticated
import com.example.fitbites.domain.auth.usecase.SignIn
import com.example.fitbites.domain.auth.usecase.SignInWithGoogle
import com.example.fitbites.domain.auth.usecase.SignOut
import com.example.fitbites.domain.auth.usecase.SignUp
import com.example.fitbites.domain.auth.usecase.SignUpWithGoogle
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
    ): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    fun provideAuthScreenUseCase(authRepository: AuthRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticated(authRepository),
        signIn = SignIn(authRepository),
        signUp = SignUp(authRepository),
        signUpWithGoogle = SignUpWithGoogle(authRepository),
        signInWithGoogle = SignInWithGoogle(authRepository),
        signOut = SignOut(authRepository)
    )

}