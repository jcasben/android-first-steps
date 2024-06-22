package com.jcasben.heroesapp

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("data") val data: DetailResponse
)

data class DetailResponse(
    @SerializedName("title") val originalName: String,
    @SerializedName("title_english") val engName: String,
    @SerializedName("synopsis") val description: String,
    @SerializedName("episodes") val episodes: String,
    @SerializedName("images") val image: AnimeImage,
    @SerializedName("score") val score: Double,
    @SerializedName("rank") val rank: Int,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("genres") val genres: List<AnimeGenre>,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String
)

data class AnimeGenre(
    @SerializedName("name") val name: String
)

data class AnimeImage(
    @SerializedName("jpg") val jpgImage: AnimeJpgImage
)

data class AnimeJpgImage(
    @SerializedName("large_image_url") val url: String
)
