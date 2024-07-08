package com.jcasben.horoscapp.motherobject

import com.jcasben.horoscapp.data.network.response.PredictionResponse
import com.jcasben.horoscapp.domain.model.HoroscopeInfo.*

object HoroscopeMotherObject {
    val anyResponse = PredictionResponse("date", "prediction", "sign")

    val horoscopeInfoList = listOf(
        Aries,
        Aquarius,
        Cancer,
        Capricorn,
        Gemini,
        Libra,
        Leo,
        Pisces,
        Sagittarius,
        Scorpio,
        Taurus,
        Virgo
    )
}