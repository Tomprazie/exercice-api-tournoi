package com.example.db

import com.example.model.Player
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object PlayerTable : IntIdTable("player") {
    val pseudo = varchar("pseudo", 50)
    val points = integer("points")
}

class PlayerDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlayerDAO>(PlayerTable)

    var pseudo by PlayerTable.pseudo
    var points by PlayerTable.points
}

fun daoToModel(playerDAO: PlayerDAO) = Player(
    playerDAO.pseudo,
    playerDAO.points
)
