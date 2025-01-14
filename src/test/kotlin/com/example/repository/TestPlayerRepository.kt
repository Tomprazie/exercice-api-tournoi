package com.example.repository

import com.example.model.Player
import com.example.model.PlayerWithRanking

class TestPlayerRepository : PlayerRepository {
    private val players = mutableListOf<Player>()

    override fun getAll(): List<Player> = players

    override fun add(player: Player) {
        players.add(player)
    }

    override fun updatePoints(pseudo: String?, points: Int?) {
        val player = players.find { it.pseudo == pseudo }
        player?.points = points ?: 0
    }

    override fun getPlayer(pseudo: String?): Player {
        return players.find { it.pseudo == pseudo } ?: Player("unknown")
    }

    override fun getRanking(pseudo: String?): Int {
        val sortedPlayers = players.sortedByDescending { it.points }
        return sortedPlayers.indexOfFirst { it.pseudo == pseudo } + 1
    }

    override fun clear() {
        players.clear()
    }

    override fun getAllByRanking(): List<PlayerWithRanking> {
        val sortedPlayers = players.sortedByDescending { it.points }
        return sortedPlayers.mapIndexed { index, player ->
            PlayerWithRanking(player, index + 1)
        }
    }
}