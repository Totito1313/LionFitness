package com.schwarckstudio.lionfitness.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface SettingsRepository {
    val livePrNotificationsEnabled: StateFlow<Boolean>
    fun setLivePrNotificationsEnabled(enabled: Boolean)
    val isDarkTheme: StateFlow<Boolean>
    fun setDarkTheme(enabled: Boolean)
}

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences("lion_fitness_settings", Context.MODE_PRIVATE)

    private val _livePrNotificationsEnabled = MutableStateFlow(prefs.getBoolean("live_pr_notifications", true))
    override val livePrNotificationsEnabled: StateFlow<Boolean> = _livePrNotificationsEnabled.asStateFlow()

    private val _isDarkTheme = MutableStateFlow(prefs.getBoolean("is_dark_theme", false))
    override val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    override fun setLivePrNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("live_pr_notifications", enabled).apply()
        _livePrNotificationsEnabled.value = enabled
    }

    override fun setDarkTheme(enabled: Boolean) {
        prefs.edit().putBoolean("is_dark_theme", enabled).apply()
        _isDarkTheme.value = enabled
    }
}
