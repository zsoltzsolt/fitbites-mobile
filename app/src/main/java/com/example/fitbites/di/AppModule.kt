package com.example.fitbites.di

import com.example.fitbites.auth.data.repository.AuthRepositoryImpl
import com.example.fitbites.auth.domain.repository.AuthRepository
import com.example.fitbites.auth.domain.usecase.AuthUseCases
import com.example.fitbites.auth.domain.usecase.IsUserAuthenticated
import com.example.fitbites.auth.domain.usecase.SignIn
import com.example.fitbites.auth.domain.usecase.SignUp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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
        signUp = SignUp(authRepository)
    )

}