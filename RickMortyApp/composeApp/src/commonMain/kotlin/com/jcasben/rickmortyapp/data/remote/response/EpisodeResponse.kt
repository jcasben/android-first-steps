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
        return when (seasonEpisode) {
            SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
            SeasonEpisode.UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp-b8c39.appspot.com/o/Rick%20and%20Morty%20Season%201%20extended%20promo.mp4?alt=media&token=336b6e7f-934b-4358-97b2-d19ec5332cec"
        }
    }
}
