package com.diadejava.api.repository.persistence

import org.jetbrains.exposed.sql.Table

private const val ID_LENGHT = 26
private const val USER_NAME_LENGHT = 200

object UserTable : Table("USER") {
    val id = varchar("ID", ID_LENGHT).primaryKey()
    val name = varchar("NAME", USER_NAME_LENGHT)
}