package com.jcasben.heroesapp

import com.jcasben.heroesapp.animes.Anime
import com.jcasben.heroesapp.animes.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v4/anime")
    suspend fun getAnimes(@Query("q") query: String): Response<AnimeResponse>

    @GET("/v4/anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: String): Response<AnimeDetailResponse>
}