package com.example.repository

import com.example.model.Player
import com.example.model.PlayerWithRanking

interface PlayerRepository {
    fun getAllPlayers(): List<Player>
    fun addPlayer(player: Player)
    fun updatePlayerPoints(pseudo: String?, points: Int?)
    fun getPlayer(pseudo: String?): Player
    fun getRanking(pseudo: String?): Int
    fun clearPlayers()
    fun getAllPlayersByRanking(): List<PlayerWithRanking>
}
