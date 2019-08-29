package com.diadejava.api.service

import com.diadejava.api.model.User

interface UserService {
    fun createUser(name: String) : User
    fun getUsers() : List<User>
}