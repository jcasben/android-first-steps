package com.jcasben.tictactoe.data.network.model

import com.jcasben.tictactoe.ui.model.GameModel
import com.jcasben.tictactoe.ui.model.PlayerModel
import com.jcasben.tictactoe.ui.model.PlayerType
import java.util.UUID

data class GameDataModel(
    val board: List<Int?>? = null,
    val gameId: String? = null,
    val player1: PlayerDataModel? = null,
    val player2: PlayerDataModel? = null,
    val playerTurn: PlayerDataModel? = null
) {
    fun toModel(): GameModel {
        return GameModel(
            board = board?.map { PlayerType.getPlayerById(it) } ?: mutableListOf(),
            gameId = gameId.orEmpty(),
            player1 = player1!!.toModel(),
            player2 = player2?.toModel(),
            playerTurn = playerTurn!!.toModel()
        )
    }
}

data class PlayerDataModel(
    val userId: String? = UUID.randomUUID().toString(),
    val playerType: Int? = null
) {
    fun toModel(): PlayerModel {
        return PlayerModel(
            userId!!,
            PlayerType.getPlayerById(playerType)
        )
    }
}
