package com.diadejava.api.service.impl

import com.diadejava.api.model.User
import com.diadejava.api.repository.UserRepository
import com.diadejava.api.service.UserService
import io.azam.ulidj.ULID
import org.slf4j.LoggerFactory
import java.lang.Exception


class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun createUser(name: String): User {
        val user = User(id = ULID.random(), name = name)
        try {
            userRepository.create(user)
            return user
        } catch (e: Exception) {
            logger.error("Error trying to create user. Exception: $e")
            throw e
        }
    }

    override fun getUsers(): List<User> {
        try{
         return userRepository.findAll()
        } catch (e: Exception) {
            logger.error("Error trying to find all users. Exception: $e ")
            throw e
        }
    }
}