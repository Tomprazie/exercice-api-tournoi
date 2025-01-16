package com.tournament.usecase

import com.tournament.usecase.addplayer.AddPlayerUseCase
import com.tournament.model.Player
import com.tournament.model.PlayerWithRanking
import com.tournament.repository.PlayerRepository
import com.tournament.usecase.deleteplayers.DeleteAllPlayersUseCase
import com.tournament.usecase.getplayer.GetPlayerWithRankingUseCase
import com.tournament.usecase.getplayers.GetAllPlayersByRankingUseCase
import com.tournament.usecase.getplayers.GetAllPlayersUseCase
import com.tournament.usecase.updateplayer.UpdatePlayerUseCase

object PlayerUseCases {
    lateinit var playerRepository: PlayerRepository

    fun getAllPlayers(): List<Player> = GetAllPlayersUseCase.execute()

    fun getAllPlayersByRanking(): List<PlayerWithRanking> = GetAllPlayersByRankingUseCase.execute()

    fun addPlayer(player: Player) = AddPlayerUseCase.execute(player)

    fun clearPlayers() = DeleteAllPlayersUseCase.execute()

    fun getPlayerWithRanking(pseudo: String?) = GetPlayerWithRankingUseCase.execute(pseudo!!)

    fun updatePlayerPoints(pseudo: String?, points: Int?) = UpdatePlayerUseCase.execute(pseudo!!, points!!)
}
