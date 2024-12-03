package com.example.fitbites.presentation.onboarding

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitbites.domain.profile.usecase.ProfileUseCases
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.fitbites.domain.profile.model.UserProfile
import com.example.fitbites.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
) : ViewModel() {

    var userProfile = mutableStateOf(UserProfile())
        private set

    fun updateName(name: String) {
        userProfile.value = userProfile.value.copy(name = name)
    }

    fun updateGoal(goal: String) {
        userProfile.value = userProfile.value.copy(goal = goal)
    }

    fun updateGender(gender: String) {
        userProfile.value = userProfile.value.copy(gender = gender)
    }

    fun updateActivityLevel(activityLevel: String) {
        userProfile.value = userProfile.value.copy(activityLevel = activityLevel)
    }

    fun updateAge(age: String) {
        userProfile.value = userProfile.value.copy(age = if (age.isNotEmpty()) age.toInt() else 0)
    }

    fun updateWeight(weight: String) {
        userProfile.value = userProfile.value.copy(weight = if (weight.isNotEmpty()) weight.toInt() else 0)
    }

    fun updateSetupStatus(setupStatus: Boolean){
        userProfile.value = userProfile.value.copy(setupComplete = setupStatus)
    }

    fun updateProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            profileUseCases.updateProfile(userProfile).collect { response ->
                when (response) {
                    is Response.Loading -> {  }
                    is Response.Success -> {
                        Log.d("DATA", "Profile updated!")
                    }
                    is Response.Error -> {
                        Log.d("DATA", "ERROR")
                    }
                }
            }
        }
    }
}