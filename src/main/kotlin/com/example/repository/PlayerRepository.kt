package com.example.repository

import com.example.model.Player
import com.example.model.PlayerWithRanking

interface PlayerRepository {
    fun getAll(): List<Player>
    fun add(player: Player)
    fun updatePoints(pseudo: String?, points: Int?)
    fun getPlayer(pseudo: String?): Player
    fun getRanking(pseudo: String?): Int
    fun clear()
    fun getAllByRanking(): List<PlayerWithRanking>
}
