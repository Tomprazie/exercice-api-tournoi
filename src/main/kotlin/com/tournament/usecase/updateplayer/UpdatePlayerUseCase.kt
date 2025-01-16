package com.tournament.usecase.updateplayer

import com.tournament.usecase.PlayerUseCases.playerRepository

class UpdatePlayerUseCase {
    companion object {
        fun execute(pseudo: String, points: Int) {
            playerRepository.updatePlayerPoints(pseudo, points)
        }
    }
}
