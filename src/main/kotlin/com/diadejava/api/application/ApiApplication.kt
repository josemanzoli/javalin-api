package com.diadejava.api.application

import com.diadejava.api.controller.UserController
import com.diadejava.api.koin.userModule
import com.diadejava.api.repository.persistence.UserTable
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class ApiApplication : KoinComponent {

    private val userController by inject<UserController>()

    fun startApplication(): Javalin {
        val app = Javalin.create { config ->
            config.defaultContentType = "application/json"
            JavalinJackson.configure(jacksonObjectMapper())
        }.apply {
            exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("not found") }
            routes{
                post("users", userController::createUser)
                get("users", userController::getUsers)
            }
        }.start(7000)
        return app
    }

    fun connectDatabase() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
    }
}

fun main() {
    startKoin {
        modules(userModule)
    }
    ApiApplication().connectDatabase()
    ApiApplication().startApplication()
}