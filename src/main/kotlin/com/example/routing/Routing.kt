package com.example.routing

import com.example.model.Player
import com.example.repository.PlayerRepository
import com.example.useCases.PlayerUseCases
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val playerRepository: PlayerRepository by inject()
    PlayerUseCases.playerRepository = playerRepository

    routing {
        route("/players") {
            get {
                val players = PlayerUseCases.getAllPlayers()
                call.respond(players)
            }

            get("/ranking") {
                val playersWithRanking = PlayerUseCases.getAllPlayersByRanking()
                call.respond(playersWithRanking)
            }

            post("/add") {
                val player = call.receive<Player>()
                PlayerUseCases.addPlayer(player)
                call.respond(HttpStatusCode.Created)
            }

            delete {
                PlayerUseCases.clearPlayers()
                call.respond(HttpStatusCode.OK)
            }

            route("/{pseudo}") {
                get {
                    val pseudo = call.parameters["pseudo"]
                    val playerWithRanking = PlayerUseCases.getPlayerWithRanking(pseudo)
                    call.respond(playerWithRanking)
                }

                put("/points") {
                    val pseudo = call.parameters["pseudo"]
                    val points = call.receive<Int>()
                    PlayerUseCases.updatePlayerPoints(pseudo, points)
                    call.respond(HttpStatusCode.OK)
                }
            }


        }
    }
}
