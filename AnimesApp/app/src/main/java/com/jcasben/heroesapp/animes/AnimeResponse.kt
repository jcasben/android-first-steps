package com.jcasben.heroesapp.animes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Anime(
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("picture_url") val picUrl: String,
    @Expose @SerializedName("myanimelist_id") val id: String
)