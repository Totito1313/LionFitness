package com.schwarckstudio.lionfitness.core.model

import com.google.firebase.Timestamp

data class SocialPost(
    val id: String = "",
    val userId: String = "",
    val workoutLogId: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val caption: String = "",
    val likes: List<String> = emptyList(), // User IDs
    val commentCount: Int = 0
)

data class Comment(
    val id: String = "",
    val userId: String = "",
    val text: String = "",
    val timestamp: Timestamp = Timestamp.now()
)
