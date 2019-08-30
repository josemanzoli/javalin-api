package com.diadejava.api.controller

import com.diadejava.api.service.UserService
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.apache.logging.log4j.LogManager

class UserController(private val userService: UserService) {

    private val logger = LogManager.getLogger(UserController::class.java)

    fun createUser(context: Context) {
        try{
            val userRequest = context.body<UserRequest>()

            logger.info("About to insert user with name:${userRequest.name}")

            val user = userService.createUser(userRequest.name)

            logger.info("User created with id: ${user.id}")

            context.json(user)
        } catch (e: BadRequestResponse) {
            logger.error("Error trying to create user. Exception: $e")
            throw e;
        }
    }

    fun getUsers(context: Context) {
        context.json(userService.getUsers())
    }
}