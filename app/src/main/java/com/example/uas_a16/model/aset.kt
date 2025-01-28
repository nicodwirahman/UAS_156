package com.example.uas_a16.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aset")
data class Aset(
    @PrimaryKey(autoGenerate = true) val idAset: Int = 0,
    val namaAset: String,
    val idKategori: Int
)
