package com.example.fitbites.repository.auth

import android.media.FaceDetector
import com.example.fitbites.domain.auth.repository.AuthRepository
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.utils.Response
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
  private val auth: FirebaseAuth,
  private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun isUserAuthenticatedInFirebase(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            if (auth.currentUser != null) {
                emit(Response.Success(true))
            } else {
                emit(Response.Success(false))
            }
        } catch (e: Exception) {
            emit(Response.Success(false))
        }
    }

    override fun isSetupCompleted(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val currentUser = auth.currentUser
            if (currentUser == null) {
                emit(Response.Error("No user is logged in."))
                return@flow
            }

            val userDoc = firestore.collection("users").document(currentUser.uid).get().await()

            val isSetupComplete = userDoc.getBoolean("isSetupComplete") ?: false
            emit(Response.Success(isSetupComplete))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred while checking setup status."))
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user

            if (user != null) {
                if (user.isEmailVerified) {
                    emit(Response.Success(true))
                } else {
                    emit(Response.Error("Email not verified. Please verify your email and try again."))
                    auth.signOut()
                }
            } else {
                emit(Response.Error("Authentication failed. Please try again."))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred."))
        }
    }

    override suspend fun signUp(email: String, password: String, username: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            val uid = result.user?.uid

            if (uid != null) {
                val emailVerificationTask = user!!.sendEmailVerification()
                emailVerificationTask.await()
                auth.signOut()

                var userProfile = UserProfile(username)

                firestore.collection("users").document(uid)
                    .set(userProfile)
                    .await()

                emit(Response.Success(true))
            } else {
                emit(Response.Error("Account creation failed. Please try again."))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred during sign-up."))
        }
    }



    override suspend fun signUpWithGoogle(idToken: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            if (authResult.additionalUserInfo?.isNewUser == true) {
                emit(Response.Success(true))
            } else {
                emit(Response.Success(false))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error"))
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            if (authResult.additionalUserInfo?.isNewUser == false) {
                emit(Response.Success(true))
            } else {
                emit(Response.Success(false))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error signing in with Google"))
        }
    }

    override suspend fun signInWithFacebook(idToken: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val credential = FacebookAuthProvider.getCredential(idToken)
            val authResult = auth.signInWithCredential(credential).await()

            if (authResult.additionalUserInfo?.isNewUser == false) {
                emit(Response.Success(true))
            } else {
                emit(Response.Error("Email not registered"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error signing in with Facebook"))
        }
    }

    override suspend fun signUpWithFacebook(idToken: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val credential = FacebookAuthProvider.getCredential(idToken)
            val authResult = auth.signInWithCredential(credential).await()

            if (authResult.user != null) {
                emit(Response.Success(true))
            } else {
                emit(Response.Error("Authentication failed!"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error signing in with Facebook"))
        }
    }

    override suspend fun signOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch(e: Exception) {
            emit(Response.Error(e.message ?: "Error signing out"))
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Flow<Response<Boolean>> = flow{
        try {
            emit(Response.Loading)
            auth.sendPasswordResetEmail(email).await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Eroare necunoscuta"))
        }
    }

    override suspend fun sendEmailVerification(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)

            val user = auth.currentUser
            if (user != null) {
                user.sendEmailVerification().await()
                emit(Response.Success(true))
            } else {
                emit(Response.Error("Eroare"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Failed to send verification email"))
        }
    }

}