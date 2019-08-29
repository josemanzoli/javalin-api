package com.diadejava.api.controller

import com.diadejava.api.service.UserService
import io.javalin.http.Context

class UserController(private val userService: UserService) {

    fun createUser(context: Context) {
        val userRequest = context.body<UserRequest>()
        context.json(userService.createUser(userRequest.name))
    }

    fun getUsers(context: Context) {
        context.json(userService.getUsers())
    }
}