package com.diadejava.api.koin

import com.diadejava.api.controller.UserController
import com.diadejava.api.repository.UserRepository
import com.diadejava.api.service.UserService
import com.diadejava.api.service.impl.UserServiceImpl
import org.koin.dsl.module

val userModule = module {
    single { UserController(get()) }
    single { UserServiceImpl(get()) as UserService }
    single { UserRepository() }
}