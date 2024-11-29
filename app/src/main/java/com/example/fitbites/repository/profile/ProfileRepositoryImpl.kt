package com.example.fitbites.repository.profile

import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.domain.profile.repository.ProfileRepository
import com.example.fitbites.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProfileRepository {
    override suspend fun fetchProfile(): Flow<Response<UserProfile>> = flow {
        emit(Response.Error("Not implemented"))
    }

    override suspend fun updateProfile(userProfile: UserProfile): Flow<Response<Boolean>> = flow {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            try {
                val userDocRef = firestore.collection("users").document(currentUser.uid)

                val updatedProfile = hashMapOf(
                    "name" to userProfile.name,
                    "goal" to userProfile.goal,
                    "gender" to userProfile.gender,
                    "activityLevel" to userProfile.activityLevel,
                    "height" to userProfile.height,
                    "weight" to userProfile.weight,
                    "age" to userProfile.age
                ) as Map<String, Any>

                userDocRef.update(updatedProfile).await()

                emit(Response.Success(true))

            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "Unknown error"))
            }
        } else {
            emit(Response.Error("User is not authenticated"))
        }
    }

    override suspend fun deleteProfile(): Flow<Response<Boolean>> = flow {
        emit(Response.Error("Not implemented"))
    }
}