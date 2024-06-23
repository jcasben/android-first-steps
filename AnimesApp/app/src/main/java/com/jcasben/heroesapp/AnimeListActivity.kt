package com.jcasben.heroesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcasben.heroesapp.databinding.ActivityAnimeListBinding
import com.jcasben.heroesapp.animes.AnimeAdapter
import com.jcasben.heroesapp.animes.AnimeResponse
import com.jcasben.heroesapp.details.DetailsAnimeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var animeAdapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        animeAdapter = AnimeAdapter{ animeId -> navigateToDetail(animeId) }
        binding.rvAnimes.setHasFixedSize(true)
        binding.rvAnimes.layoutManager = LinearLayoutManager(this)
        binding.rvAnimes.adapter = animeAdapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<AnimeResponse> = retrofit.create(ApiService::class.java).getAnimes(query)
            if (response.isSuccessful) {
                Log.i("test", "va bien")
                val res: AnimeResponse? = response.body()
                if (res != null) {
                    Log.i("test", res.toString())
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        animeAdapter.updateList(res.animes)
                    }
                }
            } else {
                println(response.toString())
                Log.i("test", "no va bien")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailsAnimeActivity::class.java)
        intent.putExtra(DetailsAnimeActivity.EXTRA_ID, id)
        startActivity(intent)
    }
}