package com.example.uas_a16.ui.ViewModel.pengeluaran

import com.example.uas_a16.model.Pengeluaran




data class InsertPengeluaranUiState(
    val insertPengeluaranEvent: InsertPengeluaranEvent = InsertPengeluaranEvent(),
    val isSuccess: Boolean = false,
    val successMessage: String = "",
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showDialog: Boolean = false // Untuk menampilkan dialog konfirmasi
)

data class InsertPengeluaranEvent(
    val idAset: Int = 0,
    val idKategori: Int = 0,
    val total: Double = 0.0,
    val tanggalTransaksi: String = "", // default to current date
    val catatan: String = ""
)

fun InsertPengeluaranEvent.toPengeluaran(): Pengeluaran = Pengeluaran(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)

fun Pengeluaran.toUiStatePengeluaran(): InsertPengeluaranUiState = InsertPengeluaranUiState(
    insertPengeluaranEvent = toInsertPengeluaranEvent()
)

fun Pengeluaran.toInsertPengeluaranEvent(): InsertPengeluaranEvent = InsertPengeluaranEvent(
    idAset = idAset,
    idKategori = idKategori,
    total = total,
    tanggalTransaksi = tanggalTransaksi,
    catatan = catatan
)
