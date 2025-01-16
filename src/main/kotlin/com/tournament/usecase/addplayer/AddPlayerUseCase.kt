package com.tournament.usecase.addplayer

import com.tournament.model.Player
import com.tournament.usecase.PlayerUseCases.playerRepository

class AddPlayerUseCase {
    companion object {
        fun execute(player: Player) {
            playerRepository.addPlayer(player)
        }
    }
}
