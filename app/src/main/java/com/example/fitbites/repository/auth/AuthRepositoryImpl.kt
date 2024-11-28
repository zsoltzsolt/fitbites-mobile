package com.example.fitbites.repository.auth

import com.example.fitbites.domain.auth.repository.AuthRepository
import com.example.fitbites.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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
  private val auth: FirebaseAuth
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

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<Response<Boolean>> = callbackFlow {
        try {
            this@callbackFlow.trySendBlocking(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).apply {
                this.await()
                if (this.isSuccessful) {
                    this@callbackFlow.trySendBlocking(Response.Success(true))
                } else {
                    this@callbackFlow.trySendBlocking(Response.Success(false))
                }
            }
        } catch (e: Exception) {
            this@callbackFlow.trySendBlocking(Response.Error(e.message ?: "Error"))
        }

        awaitClose {
            channel.close()
            cancel()
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Flow<Response<Boolean>> =
        callbackFlow {
            try {
                this@callbackFlow.trySendBlocking(Response.Loading)
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    if (it.user != null) {
                        this@callbackFlow.trySendBlocking(Response.Success(true))
                    }
                }.addOnFailureListener {
                    this@callbackFlow.trySendBlocking(Response.Error(it.message ?: "Error"))
                }
            } catch (e: Exception) {
                this@callbackFlow.trySendBlocking(Response.Error(e.message ?: "Error"))
            }
            awaitClose {
                channel.close()
                cancel()
            }
        }

    override suspend fun signUpWithGoogle(idToken: String): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(credential).await()
            if (authResult.user != null) {
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
            if (authResult.user != null) {
                emit(Response.Success(true))
            } else {
                emit(Response.Success(false))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Error signing in with Google"))
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

}