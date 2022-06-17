package com.ryudith.lab6.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModelUser (app : Application) : AndroidViewModel(app) {
    private val db : DatabaseUser
    private  val repo : RepositoryUser

    init {
        db = DatabaseUser.getDB(app)
        repo = RepositoryUser(db.daoUser())
    }

    fun add (user: EntityUser) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

    fun edit (user : EntityUser) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.editUser(user)
        }
    }

    fun read (id : Long) : LiveData<EntityUser> {
        return repo.readUser(id)
    }

    fun deleteUser (id : Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUser(id)
        }
    }

    fun deleteAll () {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }

//    fun browse () : LiveData<List<EntityUser>> {
//        return repo.browseUser
//    }

    fun browse () : Flow<PagingData<EntityUser>> {
        return Pager(PagingConfig(pageSize = 10)) {
            repo.browseUser
        }.flow.cachedIn(viewModelScope)
    }
}