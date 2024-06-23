package com.jcasben.heroesapp.characters

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data") val characters: List<Character>
)

data class Character(
    @SerializedName("character") val character: CharacterEntity,
    @SerializedName("role") val role: String,
    @SerializedName("voice_actors") val actors: List<Actor>
)

data class CharacterEntity(
    @SerializedName("name") val name: String,
    @SerializedName("images") val image: CharacterImage,
)

data class CharacterImage(
    @SerializedName("jpg") val jpgImage: CharacterJpgImage
)

data class CharacterJpgImage(
    @SerializedName("image_url") val url: String
)

data class Actor(
    @SerializedName("person") val person: Person,
    @SerializedName("language") val language: String
)

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("images") val personImage: CharacterImage
)
