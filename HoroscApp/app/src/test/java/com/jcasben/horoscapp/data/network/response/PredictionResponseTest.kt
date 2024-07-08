package com.jcasben.horoscapp.data.network.response

import com.jcasben.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test

class PredictionResponseTest {

    @Test
    fun `toDomain should return a correct PredictionModel`() {
        // Given
        val predictionResponse = anyResponse

        // When
        val predictionModel = predictionResponse.toDomain()

        // Then
        predictionModel.sign shouldBe predictionResponse.sign
        predictionModel.prediction shouldBe predictionResponse.prediction
    }
}