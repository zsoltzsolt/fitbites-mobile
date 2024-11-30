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
        val currentUser = auth.currentUser
        if (currentUser != null) {
            try {
                val userDocRef = firestore.collection("users").document(currentUser.uid)
                val documentSnapshot = userDocRef.get().await()

                if (documentSnapshot.exists()) {
                    val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                    if (userProfile != null) {
                        emit(Response.Success(userProfile))
                    } else {
                        emit(Response.Error("User profile data is corrupted"))
                    }
                } else {
                    emit(Response.Error("Profile not found"))
                }
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: "Unknown error"))
            }
        } else {
            emit(Response.Error("User is not authenticated"))
        }
    }

    override suspend fun updateProfile(userProfile: UserProfile): Flow<Response<Boolean>> = flow {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            try {
                val userDocRef = firestore.collection("users").document(currentUser.uid)

                val updateData = mutableMapOf<String, Any>()

                if (userProfile.name.isNotEmpty()) {
                    updateData["name"] = userProfile.name
                }
                if (userProfile.goal.isNotEmpty()) {
                    updateData["goal"] = userProfile.goal
                }
                if (userProfile.gender.isNotEmpty()) {
                    updateData["gender"] = userProfile.gender
                }
                if (userProfile.activityLevel.isNotEmpty()) {
                    updateData["activityLevel"] = userProfile.activityLevel
                }
                if (userProfile.height != 0) {
                    updateData["height"] = userProfile.height
                }
                if (userProfile.weight != 0) {
                    updateData["weight"] = userProfile.weight
                }
                if (userProfile.age != 0) {
                    updateData["age"] = userProfile.age
                }
                updateData["setupComplete"] = userProfile.setupComplete

                if (updateData.isNotEmpty()) {
                    userDocRef.update(updateData).await()
                }

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