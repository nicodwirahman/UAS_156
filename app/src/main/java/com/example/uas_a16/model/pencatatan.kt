package com.example.uas_a16.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Data untuk Pendapatan
@Serializable
data class Pendapatan(
    val idPendapatan: String,
    @SerialName("id_aset") val idAset: String,
    @SerialName("id_kategori") val idKategori: String,
    @SerialName("tanggal_transaksi") val tanggalTransaksi: String,
    val total: Double,
    val catatan: String
)

@Serializable
data class AllPendapatanResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pendapatan>
)

@Serializable
data class PendapatanDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pendapatan
)

// Data untuk Pengeluaran
@Serializable
data class Pengeluaran(
    val idPengeluaran: String,
    @SerialName("id_aset") val idAset: String,
    @SerialName("id_kategori") val idKategori: String,
    @SerialName("tanggal_transaksi") val tanggalTransaksi: String,
    val total: Double,
    val catatan: String
)

@Serializable
data class AllPengeluaranResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pengeluaran>
)

@Serializable
data class PengeluaranDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pengeluaran
)

// Data untuk Kategori
@Serializable
data class Kategori(
    val idKategori: String,
    val namaKategori: String
)

@Serializable
data class AllKategoriResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kategori>
)

@Serializable
data class KategoriDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kategori
)

// Data untuk Aset
@Serializable
data class Aset(
    val idAset: String,
    val namaAset: String,
    @SerialName("id_kategori") val idKategori: String
)

@Serializable
data class AllAsetResponse(
    val status: Boolean,
    val message: String,
    val data: List<Aset>
)

@Serializable
data class AsetDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Aset
)

// Data untuk Saldo dan Manajemen Keuangan
@Serializable
data class SaldoResponse(
    val status: Boolean,
    val message: String,
    val totalPendapatan: Double,
    val totalPengeluaran: Double,
    val saldo: Double
)
