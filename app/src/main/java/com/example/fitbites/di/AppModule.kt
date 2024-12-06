package com.example.fitbites.di

import com.example.fitbites.repository.auth.AuthRepositoryImpl
import com.example.fitbites.domain.auth.repository.AuthRepository
import com.example.fitbites.domain.auth.usecase.AuthUseCases
import com.example.fitbites.domain.auth.usecase.IsSetupCompleted
import com.example.fitbites.domain.auth.usecase.IsUserAuthenticated
import com.example.fitbites.domain.auth.usecase.SendEmailVerification
import com.example.fitbites.domain.auth.usecase.SendPasswordResetEmail
import com.example.fitbites.domain.auth.usecase.SignIn
import com.example.fitbites.domain.auth.usecase.SignInWithFacebook
import com.example.fitbites.domain.auth.usecase.SignInWithGoogle
import com.example.fitbites.domain.auth.usecase.SignOut
import com.example.fitbites.domain.auth.usecase.SignUp
import com.example.fitbites.domain.auth.usecase.SignUpWithFacebook
import com.example.fitbites.domain.auth.usecase.SignUpWithGoogle
import com.example.fitbites.domain.dashboard.repository.DashboardRepository
import com.example.fitbites.domain.dashboard.usecase.DashboardUseCases
import com.example.fitbites.domain.dashboard.usecase.DecrementDailyWaterIntake
import com.example.fitbites.domain.dashboard.usecase.GetCurrentWaterIntake
import com.example.fitbites.domain.dashboard.usecase.IncrementDailyWaterIntake
import com.example.fitbites.domain.dashboard.usecase.InitializeDailyWaterIntake
import com.example.fitbites.domain.profile.repository.ProfileRepository
import com.example.fitbites.domain.profile.usecase.DeleteProfile
import com.example.fitbites.domain.profile.usecase.FetchProfile
import com.example.fitbites.domain.profile.usecase.ProfileUseCases
import com.example.fitbites.domain.profile.usecase.UpdateProfile
import com.example.fitbites.repository.dashboard.DashboardRepositoryImpl
import com.example.fitbites.repository.profile.ProfileRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFirebaseFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(auth, firestore)

    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): ProfileRepository = ProfileRepositoryImpl(auth, firestore)

    @Provides
    fun provideDashboardRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): DashboardRepository = DashboardRepositoryImpl(auth, firestore)

    @Provides
    fun provideAuthScreenUseCase(authRepository: AuthRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticated(authRepository),
        isSetupCompleted = IsSetupCompleted(authRepository),
        signIn = SignIn(authRepository),
        signUp = SignUp(authRepository),
        signUpWithGoogle = SignUpWithGoogle(authRepository),
        signInWithGoogle = SignInWithGoogle(authRepository),
        signOut = SignOut(authRepository),
        sendPasswordResetEmail = SendPasswordResetEmail(authRepository),
        sendEmailVerification = SendEmailVerification(authRepository),
        signInWithFacebook = SignInWithFacebook(authRepository),
        signUpWithFacebook = SignUpWithFacebook(authRepository)
    )

    @Provides
    fun provideProfileScreenUseCase(profileRepository: ProfileRepository) = ProfileUseCases(
        fetchProfile = FetchProfile(profileRepository),
        updateProfile = UpdateProfile(profileRepository),
        deleteProfile = DeleteProfile(profileRepository)
    )

    @Provides
    fun provideDashboardUseCases(repository: DashboardRepository): DashboardUseCases {
        return DashboardUseCases(
            initializeDailyWaterIntake = InitializeDailyWaterIntake(repository),
            incrementDailyWaterIntake = IncrementDailyWaterIntake(repository),
            decrementDailyWaterIntake = DecrementDailyWaterIntake(repository),
            getCurrentWaterIntake = GetCurrentWaterIntake(repository)
        )
    }

}