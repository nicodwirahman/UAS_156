package com.example.uas_a16.ui.ViewModel.pendapatan


data class InsertPendapatanEvent(
    val idAset: Int = 0,
    val idKategori: Int = 0,
    val total: Double = 0.0,
    val tanggalTransaksi: String = "", // default to current date
    val catatan: String = ""
)

data class InsertPendapatanUiState(
    val insertPendapatanEvent: InsertPendapatanEvent = InsertPendapatanEvent()
)

data class Pendapatan(
    val idAset: Int,
    val idKategori: Int,
    val total: Double,
    val tanggalTransaksi: String,
    val catatan: String
)

// Fungsi konversi
fun InsertPendapatanEvent.toPendapatan(): Pendapatan = Pendapatan(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)

fun Pendapatan.toInsertPendapatanEvent(): InsertPendapatanEvent = InsertPendapatanEvent(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)

fun Pendapatan.toUiStatePendapatan(): InsertPendapatanUiState = InsertPendapatanUiState(
    insertPendapatanEvent = toInsertPendapatanEvent()
)