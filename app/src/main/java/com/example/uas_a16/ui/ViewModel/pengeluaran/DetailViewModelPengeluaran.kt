package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.model.Pengeluaran
import com.example.uas_a16.ui.navigasi.DestinasiUpdate
import kotlinx.coroutines.launch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class DetailPengeluaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pengeluaranRepository: PengeluaranRepository
) : ViewModel() {

    private val idAset: Int = savedStateHandle[DestinasiUpdate.Kode]
        ?: throw IllegalArgumentException("ID Aset tidak valid atau null")

    var detailUiState: DetailPengeluaranUiState by mutableStateOf(DetailPengeluaranUiState())
        private set

    private val _pengeluaranByAset: LiveData<List<Pengeluaran>> =
        pengeluaranRepository.getPengeluaranByAset(idAset)

    val pengeluaranByAset: LiveData<List<Pengeluaran>>
        get() = _pengeluaranByAset

    init {
        getPengeluaranByAset()
    }

    private fun getPengeluaranByAset() {
        viewModelScope.launch {
            detailUiState = detailUiState.copy(isLoading = true)
            try {
                val result = pengeluaranRepository.getPengeluaranByAset(idAset).value
                if (result != null) {
                    detailUiState = detailUiState.copy(
                        pengeluaranList = result,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                detailUiState = detailUiState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Terjadi kesalahan tak terduga"
                )
            }
        }
    }

    // Fungsi untuk menghapus pengeluaran
    fun deletePengeluaran(pengeluaran: Pengeluaran) {
        viewModelScope.launch {
            try {
                pengeluaranRepository.deletePengeluaran(pengeluaran)
                detailUiState = detailUiState.copy(
                    isSuccess = true,
                    successMessage = "Pengeluaran berhasil dihapus"
                )
            } catch (e: Exception) {
                detailUiState = detailUiState.copy(
                    isError = true,
                    errorMessage = e.message ?: "Gagal menghapus pengeluaran"
                )
            }
        }
    }
}

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