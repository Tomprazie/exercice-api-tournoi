package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val pseudo: String,
    var points: Int = 0,
)

@Serializable
data class PlayerWithRanking(
    val player: Player,
    val ranking: Int,
)
