package com.schwarckstudio.lionfitness.core.model

import com.google.firebase.Timestamp

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String = "",
    val bodyWeight: Double = 0.0,
    val settings: UserSettings = UserSettings()
)

data class UserSettings(
    val weightUnit: String = "kg", // "kg" or "lbs"
    val theme: String = "system" // "light", "dark", "system"
)

data class BodyMetric(
    val userId: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val metricType: String = "", // "weight", "waist", etc.
    val value: Double = 0.0
)
