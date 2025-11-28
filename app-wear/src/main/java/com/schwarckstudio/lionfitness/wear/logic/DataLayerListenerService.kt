package com.schwarckstudio.lionfitness.wear.logic

import android.content.Intent
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.schwarckstudio.lionfitness.wear.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class DataLayerListenerService : WearableListenerService() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        // Logic removed to avoid conflict with Manifest meta-data auto-launch
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
