package com.tournament.usecase.getplayer

import com.tournament.model.PlayerWithRanking
import com.tournament.usecase.PlayerUseCases.playerRepository

class GetPlayerWithRankingUseCase {
    companion object {
        fun execute(pseudo: String): PlayerWithRanking {
            val player = playerRepository.getPlayer(pseudo)
            val ranking = playerRepository.getRanking(pseudo)
            return PlayerWithRanking(player, ranking)
        }
    }
}