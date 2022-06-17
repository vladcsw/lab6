package com.ryudith.lab6.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface DAOUser {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add (user : EntityUser)

    @Update
    suspend fun edit (user : EntityUser)

    @Query("SELECT * FROM user WHERE id = :id")
    fun read (id : Long) : LiveData<EntityUser>

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser (id : Long)

    @Query("DELETE FROM user")
    suspend fun deleteAll ()

    @Query("SELECT * FROM user ORDER BY id DESC")
    fun browse () : PagingSource<Int, EntityUser>
}