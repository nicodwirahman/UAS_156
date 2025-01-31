package com.example.uas_a16.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Aset(
    @PrimaryKey(autoGenerate = true)
    val idKategori: Int
)
