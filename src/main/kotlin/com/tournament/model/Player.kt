package com.tournament.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val pseudo: String,
    val points: Int = 0,
)

@Serializable
data class PlayerWithRanking(
    val player: Player,
    val ranking: Int,
)
