package com.jcasben.heroesapp.animes.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jcasben.heroesapp.AnimeDetailResponse
import com.jcasben.heroesapp.ApiService
import com.jcasben.heroesapp.R
import com.jcasben.heroesapp.databinding.ActivityDetailsAnimeBinding
import com.squareup.picasso.Picasso
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
            Log.i("test", animeDetail.raw().toString())
            if (animeDetail.body() != null) {
                Log.i("test", animeDetail.body().toString())
                runOnUiThread { createScreen(animeDetail.body()!!) }
            }
        }
    }

    private fun createScreen(anime: AnimeDetailResponse) {
        binding.tvOriginalName.text = anime.originalName
        binding.tvEnglishName.text = anime.engName
        Picasso.get().load(anime.picUrl).into(binding.ivAnimeImage)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://myanimelist.p.rapidapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}