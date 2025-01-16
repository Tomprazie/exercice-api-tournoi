package com.tournament.usecase.getplayers

import com.tournament.model.Player
import com.tournament.usecase.PlayerUseCases.playerRepository

class GetAllPlayersUseCase {
    companion object {
        fun execute(): List<Player> {
            return playerRepository.getAllPlayers()
        }
    }
}