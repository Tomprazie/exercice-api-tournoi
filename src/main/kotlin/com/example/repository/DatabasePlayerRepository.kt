package com.example.repository

import com.example.db.PlayerDAO
import com.example.db.PlayerTable
import com.example.db.daoToModel
import com.example.model.Player
import com.example.model.PlayerWithRanking
import org.jetbrains.exposed.sql.transactions.transaction

class DatabasePlayerRepository: PlayerRepository {
    override fun getAllPlayers(): List<Player> = transaction {
        PlayerDAO.all().map(::daoToModel)
    }


    override fun addPlayer(player: Player): Unit = transaction {
        PlayerDAO.new {
            pseudo = player.pseudo
            points = player.points
        }
    }

    override fun updatePlayerPoints(pseudo: String?, points: Int?) = transaction {
        val player = PlayerDAO.find { PlayerTable.pseudo eq pseudo!! }.first()
        player.points = points!!
    }

    override fun getPlayer(pseudo: String?): Player = transaction {
        daoToModel(PlayerDAO.find { PlayerTable.pseudo eq pseudo!! }.first())
    }

    override fun getRanking(pseudo: String?): Int = transaction {
        val player = PlayerDAO.find { PlayerTable.pseudo eq pseudo!! }.first()
        PlayerDAO.all().count { it.points > player.points } + 1
    }

    override fun clearPlayers(): Unit = transaction {
        PlayerDAO.all().forEach { it.delete() }
    }

    override fun getAllPlayersByRanking(): List<PlayerWithRanking> = transaction {
        val players = getAllPlayers()
        players.sortedByDescending { it.points }
            .mapIndexed { index, player -> PlayerWithRanking(player, index + 1) }
    }
}
