package com.example

import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.*

import kotlinx.serialization.Serializable


@Serializable
data class User(val username: String, val email: String, val password: String)

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun main() {
    embeddedServer(Netty, port = 5000, module = Application::module).start(wait = true)
}

