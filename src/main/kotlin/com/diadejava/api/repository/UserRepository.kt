package com.diadejava.api.repository

import com.diadejava.api.model.User
import com.diadejava.api.repository.persistence.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun create(user: User) : String {
        return transaction { UserTable.insert {
            it[id] = user.id
            it[name] = user.name
        } get UserTable.id }
    }

    fun findAll() : List<User> {
        return transaction {
            UserTable.selectAll().map { it.toUser() }
        }
    }

    internal fun ResultRow.toUser() = User(
        id = this[UserTable.id],
        name = this[UserTable.name]
    )
}