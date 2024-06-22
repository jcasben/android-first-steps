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
import java.util.Locale

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
        binding.tvOriginalName.text = anime.data.originalName
        binding.tvEnglishName.text = anime.data.engName
        Picasso.get().load(anime.data.image.jpgImage.url).into(binding.ivAnimeImage)
        binding.tvType.text = String.format(
            "%s %s",
            getString(R.string.anime_details_type),
            anime.data.type
        )
        binding.tvEpisodes.text = String.format(
            "%s %s",
            getString(R.string.anime_details_episodes),
            anime.data.episodes
        )
        binding.tvScore.text = String.format(
            Locale.getDefault(),
            "%s %.2f",
            getString(R.string.anime_details_score),
            anime.data.score
        )
        binding.tvRank.text = String.format(
            Locale.getDefault(),
            "%s #%d",
            getString(R.string.anime_details_rank),
            anime.data.rank
        )
        binding.tvPopularity.text = String.format(
            Locale.getDefault(),
            "%s #%d",
            getString(R.string.anime_details_popularity),
            anime.data.popularity
        )
        binding.tvStatus.text = String.format(
            "%s %s",
            getString(R.string.anime_details_status),
            anime.data.status
        )
        var genres = ""
        for (g in anime.data.genres) {
            if (genres.isBlank()) {
                genres += g.name
            } else genres = String.format("%s, %s", genres, g.name)
        }
        binding.tvGenres.text = String.format(
            Locale.getDefault(),
            "%s %s",
            getString(R.string.anime_details_genres),
            genres
        )
        binding.tvSynopsis.text = anime.data.description
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}