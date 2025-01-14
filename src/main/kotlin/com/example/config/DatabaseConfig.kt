package com.example.config

import org.jetbrains.exposed.sql.Database

fun configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/playerdb",
        user = "postgres",
        password = "password"
    )
}