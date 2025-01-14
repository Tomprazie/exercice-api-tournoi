package com.example

import com.example.config.configureDatabases
import com.example.repository.DatabasePlayerRepository
import com.example.repository.PlayerRepository
import com.example.routing.configureRouting
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
