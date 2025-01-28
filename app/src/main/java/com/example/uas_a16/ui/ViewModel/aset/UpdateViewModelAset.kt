package com.example.uas_a16.ui.ViewModel.aset



import com.example.uas_a16.model.Aset

import kotlinx.coroutines.launch



// State untuk menyimpan data input Aset yang akan diupdate
data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent(),
    val errorMessage: String? = null
)

// Data class untuk menyimpan event input pengguna
data class UpdateUiEvent(
    val idAset: String = "",
    val namaAset: String = "",
    val idKategori: String = ""
)

// Extension function untuk mengkonversi UpdateUiEvent ke model Aset
fun UpdateUiEvent.toAset(): Aset = Aset(
    idAset = idAset.toInt(), // Konversi String ke Int
    namaAset = namaAset,
    idKategori = idKategori.toInt() // Konversi String ke Int
)

// Extension function untuk mengkonversi model Aset ke UpdateUiEvent
fun Aset.toUpdateUiEvent(): UpdateUiEvent {
    return UpdateUiEvent(
        idAset = this.idAset.toString(),
        namaAset = this.namaAset,
        idKategori = this.idKategori.toString()
    )
}