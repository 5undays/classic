package com.cinema.classic.data.local

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        Result.success()
                    }
                }
            } else {
                Result.failure()
            }
        } catch (ex: Exception) {
            //Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
    }
}
