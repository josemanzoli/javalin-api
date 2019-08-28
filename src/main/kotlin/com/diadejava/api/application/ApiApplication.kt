package com.diadejava.api.application

import com.diadejava.api.controller.UserController
import com.fasterxml.jackson.databind.ObjectMapper
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.plugin.json.JavalinJackson

fun main() {

    val userController = UserController()

    val app = Javalin.create { config ->
        config.defaultContentType = "application/json"
        JavalinJackson.configure(ObjectMapper())
    }.apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(7000)

    app.routes {
        get("/users") { ctx ->
            ctx.json(userController.getUser())
        }
    }
}