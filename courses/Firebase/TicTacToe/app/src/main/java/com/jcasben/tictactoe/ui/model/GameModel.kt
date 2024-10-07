package com.jcasben.tictactoe.ui.model

import com.jcasben.tictactoe.data.network.model.GameDataModel
import com.jcasben.tictactoe.data.network.model.PlayerDataModel

data class GameModel(
    val board: List<PlayerType>,
    val player1: PlayerModel,
    val player2: PlayerModel?,
    val playerTurn: PlayerModel,
    val gameId: String,
    val isGameReady: Boolean = false,
    val isMyTurn: Boolean = false
) {
    fun toData(): GameDataModel {
        return GameDataModel(
            board = board.map { it.id },
            gameId = gameId,
            player1 = player1.toData(),
            player2 = player2?.toData(),
            playerTurn = playerTurn.toData(),
        )
    }
}

data class PlayerModel(
    val userId: String,
    val playerType: PlayerType
) {
    fun toData(): PlayerDataModel {
        return PlayerDataModel(
            userId = userId,
            playerType = playerType.id
        )
    }
}

sealed class PlayerType(val id: Int, val symbol: String) {
    data object FirstPlayer: PlayerType(2, "X")
    data object SecondPlayer: PlayerType(3, "O")
    data object Empty: PlayerType(0, "")

    companion object {
        fun getPlayerById(id: Int?): PlayerType {
            return when(id) {
                FirstPlayer.id -> FirstPlayer
                SecondPlayer.id -> SecondPlayer
                else -> Empty
            }
        }
    }
}