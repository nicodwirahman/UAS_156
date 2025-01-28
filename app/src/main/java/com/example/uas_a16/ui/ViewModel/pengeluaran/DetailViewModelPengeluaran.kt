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

    init {
        getPengeluaranByAset()
    }

    private fun getPengeluaranByAset() {
        viewModelScope.launch {
            detailUiState = detailUiState.copy(isLoading = true)
            try {
                // Mengambil semua pengeluaran
                val pengeluaranList = pengeluaranRepository.getAllPengeluaran()

                // Sesuaikan filter berdasarkan atribut yang ada pada objek Pengeluaran
                val filteredPengeluaranList = pengeluaranList.filter { pengeluaran ->
                    pengeluaran.idAset == idAset  // Gantilah `asetId` dengan atribut yang ada pada objek Pengeluaran
                }

                detailUiState = detailUiState.copy(
                    pengeluaranList = filteredPengeluaranList,
                    isLoading = false
                )
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
                // Pastikan pengeluaran.id adalah ID yang valid untuk dihapus
                pengeluaranRepository.deletePengeluaran(pengeluaran.idAset)  // Menggunakan `id` dari objek Pengeluaran
                detailUiState = detailUiState.copy(
                    isSuccess = true,
                    successMessage = "Pengeluaran berhasil dihapus"
                )
                // Mengupdate daftar setelah penghapusan
                getPengeluaranByAset()
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

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertPengeluaranEvent()
}
