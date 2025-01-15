package com.tournament

import com.tournament.config.configureDatabases
import com.tournament.repository.DatabasePlayerRepository
import com.tournament.repository.PlayerRepository
import com.tournament.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(appModule)
    }
    configureSerialization()
    configureDatabases()
    configureRouting()
}

val appModule = module {
    single<PlayerRepository> { DatabasePlayerRepository() }
}
