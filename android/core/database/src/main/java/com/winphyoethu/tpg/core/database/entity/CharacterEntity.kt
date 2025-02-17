package com.winphyoethu.tpg.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Character Entity
 */
@Entity(tableName = "character")
data class CharacterEntity (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "origin")
    val origin: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)

val mockCharacterEntity = CharacterEntity(
    id = 1,
    name = "Rick",
    origin = "Earth",
    image = "image",
    status = "status",
    createdAt = 1L
)