package com.schwarckstudio.lionfitness.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.UserRepository
import com.schwarckstudio.lionfitness.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val user: User? = null,
    val weight: Double? = null,
    val height: Double? = null,
    val isLoading: Boolean = false,
    val isDarkTheme: Boolean = false, // Placeholder for theme
    val livePrNotificationsEnabled: Boolean = true // New setting
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val exportManager: com.schwarckstudio.lionfitness.logic.ExportManager,
    private val bodyMeasurementsRepository: com.schwarckstudio.lionfitness.core.data.repository.BodyMeasurementsRepository,
    private val settingsRepository: com.schwarckstudio.lionfitness.core.data.repository.SettingsRepository,
    @dagger.hilt.android.qualifiers.ApplicationContext private val context: android.content.Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
        observeSettings()
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsRepository.livePrNotificationsEnabled.collect { enabled ->
                _uiState.value = _uiState.value.copy(livePrNotificationsEnabled = enabled)
            }
        }
        viewModelScope.launch {
            settingsRepository.isDarkTheme.collect { enabled ->
                _uiState.value = _uiState.value.copy(isDarkTheme = enabled)
            }
        }
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            combine(
                userRepository.getUserFlow(),
                bodyMeasurementsRepository.getMeasurementLogsFlow()
            ) { user, measurements ->
                val latestMeasurement = measurements.firstOrNull()
                ProfileUiState(
                    isLoading = false,
                    user = user,
                    weight = latestMeasurement?.weight,
                    height = latestMeasurement?.height,
                    isDarkTheme = _uiState.value.isDarkTheme,
                    livePrNotificationsEnabled = _uiState.value.livePrNotificationsEnabled
                )
            }.collect { newState ->
                _uiState.value = newState
            }
        }
    }
    
    fun toggleTheme() {
        settingsRepository.setDarkTheme(!_uiState.value.isDarkTheme)
    }

    fun toggleLivePrNotifications() {
        settingsRepository.setLivePrNotificationsEnabled(!_uiState.value.livePrNotificationsEnabled)
    }
    
    fun updateProfile(displayName: String, weight: Double?, height: Double?) {
        viewModelScope.launch {
            // Get or create user with auth email
            val currentUser = getUserWithAuthEmail()
            val updatedUser = currentUser.copy(displayName = displayName)
            
            _uiState.value = _uiState.value.copy(
                user = updatedUser,
                weight = weight,
                height = height
            )
            
            android.util.Log.d("ProfileViewModel", "updateProfile: saving user with displayName=$displayName, email=${updatedUser.email}")
            
            // Save measurements if changed
            if (weight != null || height != null) {
                val latest = bodyMeasurementsRepository.getLatestBodyMeasurement()
                val newLog = latest?.copy(
                    weight = weight ?: latest.weight,
                    height = height ?: latest.height,
                    date = com.google.firebase.Timestamp.now(),
                    id = "" // Force new document for history tracking
                ) ?: com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog(
                    weight = weight,
                    height = height
                )
                bodyMeasurementsRepository.saveMeasurementLog(newLog)
            }
            
            // Update user displayName in Auth/User repo
            userRepository.saveUser(updatedUser)
        }
    }

    fun updateProfilePicture(uri: android.net.Uri) {
        viewModelScope.launch {
            android.util.Log.d("ProfileViewModel", "updateProfilePicture: uri=$uri")
            val internalPath = copyToInternalStorage(uri)
            android.util.Log.d("ProfileViewModel", "updateProfilePicture: internalPath=$internalPath")
            
            if (internalPath != null) {
                val user = getUserWithAuthEmail()
                val fileUri = android.net.Uri.fromFile(java.io.File(internalPath)).toString()
                android.util.Log.d("ProfileViewModel", "updateProfilePicture: fileUri=$fileUri")
                
                val updatedUser = user.copy(photoUrl = fileUri)
                userRepository.saveUser(updatedUser)
                _uiState.value = _uiState.value.copy(user = updatedUser)
            } else {
                // Handle error (e.g. show toast or log)
                // For now, just log
                println("Failed to copy image to internal storage")
                android.util.Log.e("ProfileViewModel", "Failed to copy image to internal storage")
            }
        }
    }

    private fun getUserWithAuthEmail(): User {
        val currentUser = _uiState.value.user
        if (currentUser != null && currentUser.email.isNotEmpty()) {
            return currentUser
        }
        
        // Get email from Firebase Auth
        val authUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        val email = authUser?.email ?: ""
        val uid = authUser?.uid ?: run {
            // Use offline UID
            val androidId = android.provider.Settings.Secure.getString(
                context.contentResolver,
                android.provider.Settings.Secure.ANDROID_ID
            )
            "offline_$androidId"
        }
        
        android.util.Log.d("ProfileViewModel", "getUserWithAuthEmail: creating new user with email=$email, uid=$uid")
        
        return currentUser?.copy(email = email, uid = uid) ?: User(
            uid = uid,
            email = email,
            displayName = currentUser?.displayName ?: authUser?.displayName ?: "",
            photoUrl = currentUser?.photoUrl ?: ""
        )
    }

    private fun copyToInternalStorage(uri: android.net.Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val fileName = "profile_${System.currentTimeMillis()}.jpg"
            val file = java.io.File(context.filesDir, fileName)
            val outputStream = java.io.FileOutputStream(file)
            
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun signOut() {
        userRepository.signOut()
    }

    fun exportData() {
        exportManager.exportAllData()
    }
}
