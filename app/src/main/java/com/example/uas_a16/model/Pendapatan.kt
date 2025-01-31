package com.example.uas_a16.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Pendapatan(
    @PrimaryKey(autoGenerate = true)
    val idAset: Int,
    val idKategori: Int,
    val tanggalTransaksi: String,
    val total: Double,
    val catatan: String
)
