package com.jcasben.heroesapp.animes.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jcasben.heroesapp.AnimeDetailResponse
import com.jcasben.heroesapp.ApiService
import com.jcasben.heroesapp.R
import com.jcasben.heroesapp.databinding.ActivityDetailsAnimeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsAnimeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailsAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getAnimeInfo(id)
    }

    private fun getAnimeInfo(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val animeDetail = getRetrofit().create(ApiService::class.java).getAnimeDetails(id)
            if (animeDetail.body() != null) {
                runOnUiThread { createScreen(animeDetail.body()!!) }
            }
        }
    }

    private fun createScreen(anime: AnimeDetailResponse) {

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://myanimelist.p.rapidapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}