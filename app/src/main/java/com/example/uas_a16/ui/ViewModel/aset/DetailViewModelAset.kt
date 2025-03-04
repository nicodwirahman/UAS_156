package com.example.uas_a16.ui.ViewModel.aset

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.AsetRepository

import com.example.uas_a16.model.Aset
import com.example.uas_a16.ui.navigasi.DestinasiDetailAset
import kotlinx.coroutines.launch

class DetailViewModelAset(
    savedStateHandle: SavedStateHandle,
    private val asetRepository: AsetRepository
) : ViewModel() {
    private val idAset: Int = checkNotNull(savedStateHandle[DestinasiDetailAset.ID_ASET])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getAsetById()
    }

    private fun getAsetById() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val aset = asetRepository.getAsetById(idAset) // Gunakan repository untuk mendapatkan data Aset
                if (aset != null) {
                    detailUiState = DetailUiState(
                        detailUiEvent = aset.toDetailUiEvent(), // Mengonversi Aset ke DetailUiEvent
                        isLoading = false
                    )
                } else {
                    detailUiState = DetailUiState(
                        isLoading = false,
                        isError = true,
                        errorMessage = "Data Aset tidak ditemukan"
                    )
                }
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Terjadi kesalahan saat memuat data"
                )
            }
        }
    }
}

// State untuk menyimpan status UI dan data yang relevan
data class DetailUiState(
    val detailUiEvent: DetailUiEvent = DetailUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DetailUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DetailUiEvent()
}

// Event data yang akan ditampilkan pada UI
data class DetailUiEvent(
    val idAset: String = "",
    val namaAset: String = "",
    val idKategori: String = ""
)

// Extension function untuk mengonversi model Aset ke DetailUiEvent
fun Aset.toDetailUiEvent(): DetailUiEvent {
    return DetailUiEvent(
        idAset = this.idAset.toString(),
        namaAset = this.namaAset,
        idKategori = this.idKategori.toString()
    )
}

// Extension function untuk mengonversi DetailUiEvent kembali ke model Aset
fun DetailUiEvent.toAset(): Aset {
    return Aset(
        idAset = this.idAset.toInt(),
        namaAset = this.namaAset,
        idKategori = this.idKategori.toInt()
    )
}
