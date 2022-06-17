package com.ryudith.lab6.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class EntityUser (
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo val titulo: String,
    @ColumnInfo val descripcion: String,
    @ColumnInfo val valoracion: Int,
    @ColumnInfo val tipoTurismo: String //Recreativo, Cultural, Religioso, Gastronomico
    )