package com.jcasben.heroesapp

import com.jcasben.heroesapp.animes.Anime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "x-rapidapi-host: myanimelist.p.rapidapi.com",
        "x-rapidapi-key:573d782564msh35f31641a143d7dp1b7331jsnda4cd36d3ee2"
    )
    @GET("/v2/anime/search?n=50")
    suspend fun getAnimes(@Query("q") query: String): Response<List<Anime>>

    @Headers(
        "x-rapidapi-host: myanimelist.p.rapidapi.com",
        "x-rapidapi-key:573d782564msh35f31641a143d7dp1b7331jsnda4cd36d3ee2"
    )
    @GET("/anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: String): Response<AnimeDetailResponse>
}