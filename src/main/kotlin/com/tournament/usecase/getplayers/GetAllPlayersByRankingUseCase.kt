package com.tournament.usecase.getplayers

import com.tournament.model.PlayerWithRanking
import com.tournament.usecase.PlayerUseCases.playerRepository

class GetAllPlayersByRankingUseCase {
    companion object {
        fun execute(): List<PlayerWithRanking> {
            return playerRepository.getAllPlayersByRanking()
        }
    }
}