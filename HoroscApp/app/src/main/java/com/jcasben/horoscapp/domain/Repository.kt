package com.jcasben.horoscapp.domain

import com.jcasben.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}