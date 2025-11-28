package com.schwarckstudio.lionfitness.core.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class BodyMeasurementLog(
    @DocumentId val id: String = "",
    val userId: String = "",
    val date: Timestamp = Timestamp.now(),
    val weight: Double? = null,
    val height: Double? = null,
    val chest: Double? = null,
    val waist: Double? = null,
    val arms: Double? = null,
    val legs: Double? = null,
    val hips: Double? = null
)
