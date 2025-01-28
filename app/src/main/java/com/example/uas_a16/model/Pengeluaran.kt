package com.example.uas_a16.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "pengeluaran")
data class Pengeluaran(
    @PrimaryKey(autoGenerate = true) val idPengeluaran: Int = 0,
    val idAset: Int,
    val idKategori: Int,
    val tanggalTransaksi: String, // Format: YYYY-MM-DD
    val total: Double,
    val catatan: String
)
