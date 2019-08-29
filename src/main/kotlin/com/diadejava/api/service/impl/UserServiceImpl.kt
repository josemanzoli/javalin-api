package com.diadejava.api.service.impl

import com.diadejava.api.model.User
import com.diadejava.api.repository.UserRepository
import com.diadejava.api.service.UserService
import io.azam.ulidj.ULID


class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(name: String): String {
        val user = User(
            id = ULID.random(),
            name = name)

        return userRepository.create(user)
    }
}