package com.example.uas_a16.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Pengeluaran(
    @PrimaryKey(autoGenerate = true)
    val idAset: Int,
    val idKategori: Int,
    val tanggalTransaksi: String, // Format: YYYY-MM-DD
    val total: Double,
    val catatan: String
)
