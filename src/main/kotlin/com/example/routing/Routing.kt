package com.example.routing

import com.example.model.Player
import com.example.model.PlayerWithRanking
import com.example.repository.PlayerRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val playerRepository: PlayerRepository by inject()

    routing {
        route("/players") {
            get {
                val players = playerRepository.getAll()
                call.respond(players)
            }

            get("/ranking") {
                val playersWithRanking = playerRepository.getAllByRanking()
                call.respond(playersWithRanking)
            }

            post("/add") {
                val player = call.receive<Player>()
                playerRepository.add(player)
                call.respond(HttpStatusCode.Created)
            }

            delete {
                playerRepository.clear()
                call.respond(HttpStatusCode.OK)
            }

            route("/{pseudo}") {
                get {
                    val pseudo = call.parameters["pseudo"]
                    val player = playerRepository.getPlayer(pseudo)
                    val ranking = playerRepository.getRanking(pseudo)
                    call.respond(PlayerWithRanking(player, ranking))
                }

                put("/points") {
                    val pseudo = call.parameters["pseudo"]
                    val points = call.receive<Int>()

                    playerRepository.updatePoints(pseudo, points)
                    call.respond(HttpStatusCode.OK)
                }
            }


        }
    }
}
