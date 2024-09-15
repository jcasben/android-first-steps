package com.jcasben.tictactoe.data.network

import com.google.firebase.database.DatabaseReference
import com.jcasben.tictactoe.data.network.model.GameDataModel
import javax.inject.Inject

class FirebaseService @Inject constructor(private val databaseReference: DatabaseReference) {

    companion object {
        private const val PATH = "games"
    }

    fun createGame(gameDataModel: GameDataModel): String {
        val reference = databaseReference.child(PATH).push()
        val key = reference.key
        val game = gameDataModel.copy(gameId = key)
        reference.setValue(game)

        return game.gameId.orEmpty()
    }
}