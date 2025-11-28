package com.schwarckstudio.lionfitness.wear.data

import android.content.Context
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.schwarckstudio.lionfitness.core.model.Routine
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class WearRoutineRepository(context: Context) {

    private val dataClient = Wearable.getDataClient(context)
    private val gson = Gson()

    fun getRoutines(): Flow<List<Routine>> = callbackFlow {
        val listener = DataClient.OnDataChangedListener { dataEvents ->
            for (event in dataEvents) {
                if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == "/routines") {
                    val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                    val json = dataMap.getString("routines_list")
                    if (json != null) {
                        try {
                            val type = object : TypeToken<List<Routine>>() {}.type
                            val routines: List<Routine> = gson.fromJson(json, type)
                            trySend(routines)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        dataClient.addListener(listener)

        // Initial fetch
        try {
            val dataItemBuffer = dataClient.dataItems.await()
            for (item in dataItemBuffer) {
                if (item.uri.path == "/routines") {
                    val dataMap = DataMapItem.fromDataItem(item).dataMap
                    val json = dataMap.getString("routines_list")
                    if (json != null) {
                        try {
                            val type = object : TypeToken<List<Routine>>() {}.type
                            val routines: List<Routine> = gson.fromJson(json, type)
                            trySend(routines)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
            dataItemBuffer.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        awaitClose {
            dataClient.removeListener(listener)
        }
    }
}
