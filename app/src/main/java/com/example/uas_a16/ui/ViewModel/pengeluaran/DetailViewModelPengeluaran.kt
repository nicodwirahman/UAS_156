package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue

import com.example.uas_a16.model.Pengeluaran


data class DetailPengeluaranUiState(
    val detailUiEvent: InsertPengeluaranEvent = InsertPengeluaranEvent(),
    val pengeluaranList: List<Pengeluaran> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isSuccess: Boolean = false,
    val successMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertPengeluaranEvent()

    val isUiEventNotEmpty: Boolean // Tambahkan properti ini
        get() = detailUiEvent != InsertPengeluaranEvent()
}