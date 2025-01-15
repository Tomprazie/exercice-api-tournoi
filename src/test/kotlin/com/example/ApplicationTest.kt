package com.example

import com.example.model.Player
import com.example.model.PlayerWithRanking
import com.example.repository.PlayerRepository
import com.example.repository.TestPlayerRepository
import com.example.routing.configureRouting
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.junit.Assert.assertEquals
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ApplicationTest : KoinTest {
    private fun <R> withApp(test: suspend ApplicationTestBuilder.() -> R) = testApplication {
        application {
            module {
                configureSerialization()
                configureRouting()
            }
        }
        test()
    }
    private fun ApplicationTestBuilder.createClient() = createClient { install(ContentNegotiation) { json() } }

    private val playerRepository: PlayerRepository by inject()
    private val testModule = module {
        single<PlayerRepository> { TestPlayerRepository() }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule)
        }
        playerRepository.clearPlayers()
        playerRepository.addPlayer(Player("player1"))
        playerRepository.addPlayer(Player("player2", 10))
        playerRepository.addPlayer(Player("player3", 5))
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldReturnAllPlayer() = withApp {
        //Given
        val client = createClient()

        //When
        val response = client.get("/players")

        //Then
        val results = response.body<List<Player>>()
        assertEquals(HttpStatusCode.OK, response.status)
        val actualPlayersPseudos = results.map(Player::pseudo)
        assertContentEquals(listOf("player1", "player2", "player3"), actualPlayersPseudos)
    }

    @Test
    fun shouldCreatePlayerAndAddItToListOfPlayers() = withApp {
        val client = createClient()
        val player = Player("player4")

        val responseForPost = client.post("/players/add") {
            contentType(ContentType.Application.Json)
            setBody(player)
        }

        val results = playerRepository.getAllPlayers()
        assertEquals(HttpStatusCode.Created, responseForPost.status)
        val actualPlayersPseudos = results.map(Player::pseudo)
        assertContentEquals(
            listOf("player1", "player2", "player3", "player4"),
            actualPlayersPseudos
        )
    }

    @Test
    fun creatingPlayerShouldSetItsNumberOfPointToZero() = withApp {
        val client = createClient()
        val player = Player("player4")

        client.post("/players/add") {
            contentType(ContentType.Application.Json)
            setBody(player)
        }

        val results = playerRepository.getAllPlayers()
        val createdPlayer = results.find { it.pseudo == "player4" }
        assertEquals(0, createdPlayer?.points)
    }

    @Test
    fun shouldUpdateNumberOfPoints() = withApp {
        val client = createClient()

        val responseForPut = client.put("/players/player1/points") {
            contentType(ContentType.Application.Json)
            setBody(10)
        }

        assertEquals(HttpStatusCode.OK, responseForPut.status)
        val results = playerRepository.getAllPlayers()
        val player = results.find { it.pseudo == "player1" }
        assertEquals(10, player?.points)
    }

    @Test
    fun `should get a player`() = withApp {
        val client = createClient()

        val response = client.get("/players/player2")

        assertEquals(HttpStatusCode.OK, response.status)
        val player = response.body<PlayerWithRanking>()
        assertEquals("player2", player.player.pseudo)
        assertEquals(10, player.player.points)
        assertEquals(1, player.ranking)
    }

    @Test
    fun `should get list of players ranked by points`() = withApp {
        val client = createClient()

        val response = client.get("/players/ranking")

        assertEquals(HttpStatusCode.OK, response.status)
        val players = response.body<List<PlayerWithRanking>>()
        assertEquals(3, players.size)
        assertEquals("player2", players[0].player.pseudo)
        assertEquals(10, players[0].player.points)
        assertEquals(1, players[0].ranking)
        assertEquals("player3", players[1].player.pseudo)
        assertEquals(5, players[1].player.points)
        assertEquals(2, players[1].ranking)
        assertEquals("player1", players[2].player.pseudo)
        assertEquals(0, players[2].player.points)
        assertEquals(3, players[2].ranking)
    }

    @Test
    fun `should delete all players at the end of the tournament`() = withApp {
        val client = createClient()

        val response = client.delete("/players")

        assertEquals(HttpStatusCode.OK, response.status)
        val players = playerRepository.getAllPlayers()
        assertEquals(0, players.size)
    }
}
