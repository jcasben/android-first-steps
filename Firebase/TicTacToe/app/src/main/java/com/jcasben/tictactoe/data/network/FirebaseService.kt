package com.jcasben.tictactoe.data.network

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.jcasben.tictactoe.data.network.model.GameDataModel
import com.jcasben.tictactoe.ui.model.GameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun joinGame(gameId: String): Flow<GameModel?> {
        return databaseReference.database.reference.child("$PATH/$gameId").snapshots.map { dataSnapshot ->
            dataSnapshot.getValue(GameDataModel::class.java)?.toModel()
        }
    }

    fun updateGame(game: GameDataModel) {
        if (game.gameId != null) {
            databaseReference.child(PATH).child(game.gameId).setValue(game)
        }
    }

    fun gameExists(gameId: String): Flow<Boolean> {
        return databaseReference.child(PATH).child(gameId).snapshots.map { dataSnapshot ->
            dataSnapshot.exists()
        }
    }
}