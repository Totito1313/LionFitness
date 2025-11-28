package com.schwarckstudio.lionfitness.logic

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.schwarckstudio.lionfitness.MainActivity
import com.schwarckstudio.lionfitness.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutService : Service() {

    @Inject
    lateinit var activeWorkoutManager: ActiveWorkoutManager

    @Inject
    lateinit var restTimerManager: RestTimerManager

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val notificationManager by lazy { getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, buildNotification("Entrenamiento Activo", "00:00"))
        observeWorkoutState()
    }

    private fun observeWorkoutState() {
        serviceScope.launch {
            activeWorkoutManager.currentWorkout.collectLatest { workout ->
                if (workout == null) {
                    stopSelf()
                } else {
                    updateNotification(workout.name, "En curso")
                }
            }
        }

        serviceScope.launch {
            restTimerManager.remainingSeconds.collectLatest { seconds ->
                if (restTimerManager.isRunning.value) {
                    updateNotification("Descanso", formatTime(seconds))
                }
            }
        }
        
        serviceScope.launch {
             activeWorkoutManager.caloriesBurned.collectLatest { calories ->
                 // We could update notification with calories too
             }
        }
    }
    
    private fun updateNotification(title: String, content: String) {
        notificationManager.notify(NOTIFICATION_ID, buildNotification(title, content))
    }

    private fun buildNotification(title: String, content: String): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.mipmap.ic_launcher) // Use app icon which is safer
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Workout Service",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val CHANNEL_ID = "workout_channel"
        const val NOTIFICATION_ID = 1
    }
}
