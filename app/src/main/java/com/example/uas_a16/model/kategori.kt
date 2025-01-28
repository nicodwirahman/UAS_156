package com.example.uas_a16.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true) val idKategori: Int = 0,
    val namaKategori: String
)

