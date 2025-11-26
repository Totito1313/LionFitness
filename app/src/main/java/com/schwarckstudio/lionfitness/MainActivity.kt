package com.schwarckstudio.lionfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.schwarckstudio.lionfitness.ui.LionFitnessApp
import com.schwarckstudio.lionfitness.ui.theme.LionFitnessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @javax.inject.Inject
    lateinit var auth: com.google.firebase.auth.FirebaseAuth

    @javax.inject.Inject
    lateinit var wearableSyncManager: com.schwarckstudio.lionfitness.logic.WearableSyncManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        auth.signInAnonymously()
            .addOnSuccessListener { 
                android.util.Log.d("MainActivity", "Signed in anonymously: ${it.user?.uid}")
            }
            .addOnFailureListener {
                android.util.Log.e("MainActivity", "Sign in failed", it)
            }

        requestPermissions()

        setContent {
            LionFitnessTheme {
                LionFitnessApp()
            }
        }
    }


    private fun requestPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val permissions = arrayOf(
                android.Manifest.permission.POST_NOTIFICATIONS,
                android.Manifest.permission.ACTIVITY_RECOGNITION
            )
            
            val neededPermissions = permissions.filter {
                androidx.core.content.ContextCompat.checkSelfPermission(this, it) != android.content.pm.PackageManager.PERMISSION_GRANTED
            }
            
            if (neededPermissions.isNotEmpty()) {
                requestPermissions(neededPermissions.toTypedArray(), 101)
            }
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
             if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACTIVITY_RECOGNITION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                 requestPermissions(arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION), 101)
             }
        }
    }
}