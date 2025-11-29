package com.schwarckstudio.lionfitness.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.UserRepository
import com.schwarckstudio.lionfitness.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val settingsRepository: com.schwarckstudio.lionfitness.core.data.repository.SettingsRepository
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
            
            // Fetch latest measurements
            val latestMeasurement = bodyMeasurementsRepository.getLatestBodyMeasurement()
            
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                user = User(uid = "1", email = "alex@example.com", displayName = "Alex Zuñiga"), // Dummy
                weight = latestMeasurement?.weight,
                height = latestMeasurement?.height
            )
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
            // Update local state
            _uiState.value = _uiState.value.copy(
                user = _uiState.value.user?.copy(displayName = displayName),
                weight = weight,
                height = height
            )
            
            // Save measurements if changed
            if (weight != null || height != null) {
                val latest = bodyMeasurementsRepository.getLatestBodyMeasurement()
                val newLog = latest?.copy(
                    weight = weight ?: latest.weight,
                    height = height ?: latest.height,
                    date = com.google.firebase.Timestamp.now() // Update timestamp for new entry? Or just update latest?
                    // Ideally we create a new log if it's a new measurement, but for "Profile" edit it might be just updating static data.
                    // But since we use BodyMeasurements as source of truth, let's create a new log or update latest if it's recent.
                    // For simplicity, let's just update the UI state for now and assume BodyMeasurements screen handles history.
                    // Wait, user wants "Profile details". If we save here, it should probably be a new measurement log.
                ) ?: com.schwarckstudio.lionfitness.core.model.BodyMeasurementLog(
                    weight = weight,
                    height = height
                )
                bodyMeasurementsRepository.saveMeasurementLog(newLog)
            }
            
            // Update user displayName in Auth/User repo
            val currentUser = _uiState.value.user
            if (currentUser != null) {
                userRepository.saveUser(currentUser)
            }
        }
    }

    fun updateProfilePicture(uri: android.net.Uri) {
        viewModelScope.launch {
            val user = _uiState.value.user ?: return@launch
            val updatedUser = user.copy(photoUrl = uri.toString())
            userRepository.saveUser(updatedUser)
            _uiState.value = _uiState.value.copy(user = updatedUser)
        }
    }

    fun signOut() {
        userRepository.signOut()
    }

    fun exportData() {
        exportManager.exportAllData()
    }
}
