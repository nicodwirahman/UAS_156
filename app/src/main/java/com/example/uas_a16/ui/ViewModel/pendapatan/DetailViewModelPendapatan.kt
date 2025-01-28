package com.example.uas_a16.ui.ViewModel.pendapatan

import com.example.uas_a16.model.Pendapatan




data class DetailPendapatanUiState(
    val detailUiEvent: InsertPendapatanEvent = InsertPendapatanEvent(),
    val pendapatanList: List<Pendapatan> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertPendapatanEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertPendapatanEvent()
}

// Fungsi konversi dari Pendapatan ke InsertPendapatanEvent
fun Pendapatan.toDetailPendapatanUiEvent(): InsertPendapatanEvent {
    return InsertPendapatanEvent(
        idKategori = idKategori, // Tetap menggunakan Int
        total = total,
        tanggalTransaksi = tanggalTransaksi,
        catatan = catatan
    )
}
