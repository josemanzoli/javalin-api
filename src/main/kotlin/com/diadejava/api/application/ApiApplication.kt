package com.diadejava.api.application

import com.diadejava.api.controller.UserController
import com.diadejava.api.koin.userModule
import com.diadejava.api.model.User
import com.fasterxml.jackson.databind.ObjectMapper
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.plugin.json.JavalinJackson
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class ApiApplication : KoinComponent {

    private val userController : UserController by inject()

    fun startApplication(): Javalin {
        startKoin {
            modules(userModule)
        }

        val app = Javalin.create { config ->
            config.defaultContentType = "application/json"
            JavalinJackson.configure(ObjectMapper())
        }.apply {
            exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("not found") }
        }.start(7000)

        app.routes {
            get("/users") { ctx ->
                ctx.json(userController.createUser())
            }
        }

        return app
    }
}

fun main() {
    ApiApplication().startApplication()
}