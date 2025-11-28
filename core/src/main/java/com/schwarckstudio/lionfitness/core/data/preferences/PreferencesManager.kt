package com.schwarckstudio.lionfitness.core.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("lion_fitness_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_FIRST_RUN = "is_first_run"
    }

    var isFirstRun: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_FIRST_RUN, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_FIRST_RUN, value).apply()
}
