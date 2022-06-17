package com.ryudith.lab6.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource

class RepositoryUser (private val daoUser: DAOUser) {
//    val browseUser : LiveData<List<EntityUser>> = daoUser.browse()
    val browseUser : PagingSource<Int, EntityUser> = daoUser.browse()

    suspend fun addUser (user: EntityUser) {
        daoUser.add(user)
    }

    suspend fun editUser (user: EntityUser) {
        daoUser.edit(user)
    }

    fun readUser (id : Long) : LiveData<EntityUser> {
        return daoUser.read(id)
    }

    suspend fun deleteUser (id : Long) {
        daoUser.deleteUser(id)
    }

    suspend fun deleteAll () {
        daoUser.deleteAll()
    }
}