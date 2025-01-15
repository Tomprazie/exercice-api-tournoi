package com.example.useCases

import com.example.model.Player
import com.example.model.PlayerWithRanking
import com.example.repository.PlayerRepository

object PlayerUseCases {
    lateinit var playerRepository: PlayerRepository

    fun getAllPlayers(): List<Player> = playerRepository.getAll()

    fun getAllPlayersByRanking(): List<PlayerWithRanking> = playerRepository.getAllByRanking()

    fun addPlayer(player: Player) = playerRepository.add(player)

    fun clearPlayers() = playerRepository.clear()

    fun getPlayerWithRanking(pseudo: String?): PlayerWithRanking {
        val player = playerRepository.getPlayer(pseudo)
        val ranking = playerRepository.getRanking(pseudo)
        return PlayerWithRanking(player, ranking)
    }

    fun updatePlayerPoints(pseudo: String?, points: Int?) = playerRepository.updatePoints(pseudo, points)
}
