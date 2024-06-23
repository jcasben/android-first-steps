package com.jcasben.heroesapp

import com.jcasben.heroesapp.animes.AnimeResponse
import com.jcasben.heroesapp.characters.CharacterResponse
import com.jcasben.heroesapp.details.AnimeDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v4/anime")
    suspend fun getAnimes(@Query("q") query: String): Response<AnimeResponse>

    @GET("/v4/anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: String): Response<AnimeDetailResponse>

    @GET("/v4/anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") animeId: String): Response<CharacterResponse>
}