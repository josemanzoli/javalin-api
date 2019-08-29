package com.diadejava.api.controller

import com.diadejava.api.service.UserService

class UserController(private val userService: UserService) {

    fun createUser(): String {
        return userService.createUser("test")
    }
}