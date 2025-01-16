package com.tournament.usecase.deleteplayers

import com.tournament.usecase.PlayerUseCases.playerRepository

class DeleteAllPlayersUseCase {
    companion object {
        fun execute() {
            playerRepository.clearPlayers()
        }
    }
}