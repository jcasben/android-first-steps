package com.jcasben.horoscapp.data.providers

import com.jcasben.horoscapp.domain.model.HoroscopeInfo
import com.jcasben.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
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
}