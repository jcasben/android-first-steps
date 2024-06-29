package com.jcasben.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.jcasben.horoscapp.R
import com.jcasben.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Aquarius
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Aries
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Cancer
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Capricorn
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Gemini
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Leo
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Libra
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Pisces
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Taurus
import com.jcasben.horoscapp.domain.model.HoroscopeModel.Virgo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        horoscopeDetailViewModel.getHoroscope(args.type)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when(it) {
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.progressBar.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Aquarius -> R.drawable.detail_aquarius
            Cancer -> R.drawable.detail_cancer
            Capricorn -> R.drawable.detail_capricorn
            Gemini -> R.drawable.detail_gemini
            Libra -> R.drawable.detail_libra
            Leo -> R.drawable.detail_leo
            Pisces -> R.drawable.detail_pisces
            Sagittarius -> R.drawable.detail_sagittarius
            Scorpio -> R.drawable.detail_scorpio
            Taurus -> R.drawable.detail_taurus
            Virgo -> R.drawable.detail_virgo
        }

        binding.ivDetail.setImageResource(image)
    }

    private fun errorState() {
        binding.progressBar.isVisible = false
    }

    private fun loadingState() {
        binding.progressBar.isVisible = true
    }
}