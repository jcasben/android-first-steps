package com.jcasben.horoscapp.data

import android.util.Log
import com.jcasben.horoscapp.data.network.HoroscopeApiService
import com.jcasben.horoscapp.domain.Repository
import com.jcasben.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("test", "An error occurred: ${it.message}") }
        return null
    }
}