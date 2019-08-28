package com.diadejava.api.controller

import com.diadejava.api.model.User

class UserController {

    fun getUser(): User {
        return User("Hello World")
    }
}