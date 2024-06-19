package com.jcasben.heroesapp

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("title_ov") val originalName: String,
    @SerializedName("title_en") val engName: String,
    @SerializedName("synopsis") val description: String,
    @SerializedName("information") val animeInfo: AnimeInfo,
    @SerializedName("statistics") val stats: AnimeStatistics,
    @SerializedName("picture_url") val picUrl: String
)

data class AnimeInfo(
    @SerializedName("episodes") val episodes: String,
    @SerializedName("status") val stat: String,
    @SerializedName("genres") val genres: List<AnimeGenre>
)

data class AnimeGenre(
    @SerializedName("name") val name: String
)

data class AnimeStatistics(
    @SerializedName("score") val score: Double,
    @SerializedName("ranked") val rank: Int,
    @SerializedName("popularity") val popularity: Int
)
