package com.jcasben.heroesapp.animes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data") val animes: List<Anime>
)

data class Anime(
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("images") val image: AnimeImage,
    @Expose @SerializedName("mal_id") val id: String
)

data class AnimeImage(
    @SerializedName("jpg") val jpgImage: AnimeJpgImage
)

data class AnimeJpgImage(
    @SerializedName("large_image_url") val url: String
)