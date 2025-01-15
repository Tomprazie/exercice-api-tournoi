package com.example.useCases

import com.example.model.Player
import com.example.model.PlayerWithRanking
import com.example.repository.PlayerRepository

object PlayerUseCases {
    lateinit var playerRepository: PlayerRepository

    fun getAllPlayers(): List<Player> = playerRepository.getAllPlayers()

    fun getAllPlayersByRanking(): List<PlayerWithRanking> = playerRepository.getAllPlayersByRanking()

    fun addPlayer(player: Player) = playerRepository.addPlayer(player)

    fun clearPlayers() = playerRepository.clearPlayers()

    fun getPlayerWithRanking(pseudo: String?): PlayerWithRanking {
        val player = playerRepository.getPlayer(pseudo)
        val ranking = playerRepository.getRanking(pseudo)
        return PlayerWithRanking(player, ranking)
    }

    fun updatePlayerPoints(pseudo: String?, points: Int?) = playerRepository.updatePlayerPoints(pseudo, points)
}
