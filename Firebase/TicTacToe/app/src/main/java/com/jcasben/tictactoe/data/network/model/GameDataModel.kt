package com.jcasben.tictactoe.data.network.model

import java.util.UUID

data class GameDataModel(
    val board: List<Int?>? = null,
    val gameId: String? = null,
    val player1: PlayerDataModel? = null,
    val player2: PlayerDataModel? = null,
    val playerTurn: PlayerDataModel? = null
)

data class PlayerDataModel(
    val userId: String? = UUID.randomUUID().toString(),
    val playerType: Int? = null
)
