package com.tournament.repository

import com.tournament.model.Player
import com.tournament.model.PlayerWithRanking

class TestPlayerRepository : PlayerRepository {
    private val players = mutableListOf<Player>()

    override fun getAllPlayers(): List<Player> = players

    override fun addPlayer(player: Player) {
        players.add(player)
    }

    override fun updatePlayerPoints(pseudo: String?, points: Int?) {
        val playerIndex = players.indexOfFirst { it.pseudo == pseudo }
        if (playerIndex != -1) {
            players[playerIndex] = players[playerIndex].copy(points = points ?: 0)
        }
    }

    override fun getPlayer(pseudo: String?): Player {
        return players.find { it.pseudo == pseudo } ?: Player("unknown")
    }

    override fun getRanking(pseudo: String?): Int {
        val sortedPlayers = players.sortedByDescending { it.points }
        return sortedPlayers.indexOfFirst { it.pseudo == pseudo } + 1
    }

    override fun clearPlayers() {
        players.clear()
    }

    override fun getAllPlayersByRanking(): List<PlayerWithRanking> {
        val sortedPlayers = players.sortedByDescending { it.points }
        return sortedPlayers.mapIndexed { index, player ->
            PlayerWithRanking(player, index + 1)
        }
    }
}