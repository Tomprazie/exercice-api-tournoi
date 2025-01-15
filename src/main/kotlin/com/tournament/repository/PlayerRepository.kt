package com.tournament.repository

import com.tournament.model.Player
import com.tournament.model.PlayerWithRanking

interface PlayerRepository {
    fun getAllPlayers(): List<Player>
    fun addPlayer(player: Player)
    fun updatePlayerPoints(pseudo: String?, points: Int?)
    fun getPlayer(pseudo: String?): Player
    fun getRanking(pseudo: String?): Int
    fun clearPlayers()
    fun getAllPlayersByRanking(): List<PlayerWithRanking>
}
