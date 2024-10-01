package com.jcasben.rickmortyapp.data.remote.response

import com.jcasben.rickmortyapp.domain.model.EpisodeModel
import com.jcasben.rickmortyapp.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfter("/") },
            season = season,
            videoURL = getVideoUrlFromSeason(season)
        )
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SeasonEpisode.SEASON_1
            episode.startsWith("S02") -> SeasonEpisode.SEASON_2
            episode.startsWith("S03") -> SeasonEpisode.SEASON_3
            episode.startsWith("S04") -> SeasonEpisode.SEASON_4
            episode.startsWith("S05") -> SeasonEpisode.SEASON_5
            episode.startsWith("S06") -> SeasonEpisode.SEASON_6
            episode.startsWith("S06") -> SeasonEpisode.SEASON_7
            else -> SeasonEpisode.UNKNOWN
        }
    }

    private fun getVideoUrlFromSeason(seasonEpisode: SeasonEpisode): String {
        return when(seasonEpisode) {
            SeasonEpisode.SEASON_1 -> "https://www.youtube.com/watch?v=BFTSrbB2wII"
            SeasonEpisode.SEASON_2 -> "https://www.youtube.com/watch?v=_IZfO_LfK5Q"
            SeasonEpisode.SEASON_3 -> "https://www.youtube.com/watch?v=rLyOul8kau0"
            SeasonEpisode.SEASON_4 -> "https://www.youtube.com/watch?v=hl1U0bxTHbY"
            SeasonEpisode.SEASON_5 -> "https://www.youtube.com/watch?v=qbHYYXj2gMc"
            SeasonEpisode.SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8"
            SeasonEpisode.SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso"
            SeasonEpisode.UNKNOWN -> "https://www.youtube.com/watch?v=BFTSrbB2wII"
        }
    }
}
