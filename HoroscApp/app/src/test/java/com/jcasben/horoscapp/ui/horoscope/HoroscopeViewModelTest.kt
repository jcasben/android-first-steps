package com.jcasben.horoscapp.ui.horoscope

import com.jcasben.horoscapp.data.providers.HoroscopeProvider
import com.jcasben.horoscapp.motherobject.HoroscopeMotherObject
import com.jcasben.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this,  relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopes are loaded`() {
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList

        // Given
        val viewModel = HoroscopeViewModel(horoscopeProvider)

        // When
        val horoscopes = viewModel.horoscope.value

        // Then
        assertTrue(horoscopes.isNotEmpty())
    }
}