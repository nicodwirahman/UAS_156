package com.example.uas_a16.ui.ViewModel.aset



import com.example.uas_a16.model.Aset
// State untuk menyimpan data input Aset
data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

// Data class untuk menyimpan event input pengguna
data class InsertUiEvent(
    val idAset: String = "",
    val namaAset: String = "",
    val idKategori: String = ""
)

// Extension function untuk mengkonversi InsertUiEvent ke model Aset
fun InsertUiEvent.toAset(): Aset = Aset(
    idAset = idAset.toInt(), // Konversi String ke Int
    namaAset = namaAset,
    idKategori = idKategori.toInt() // Konversi String ke Int
)

// Extension function untuk mengkonversi model Aset ke InsertUiState
fun Aset.toUiStateAset(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

// Extension function untuk mengkonversi model Aset ke InsertUiEvent
fun Aset.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idAset = idAset.toString(),
    namaAset = namaAset,
    idKategori = idKategori.toString()
)