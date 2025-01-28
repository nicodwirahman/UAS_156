package com.example.uas_a16.ui.ViewModel.aset



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.model.Aset

import kotlinx.coroutines.launch

class UpdateAsetViewModel(private val asetRepository: AsetRepository) : ViewModel() {
    // State untuk menyimpan data input Aset yang akan diupdate
    var uiState by mutableStateOf(UpdateUiState())
        private set

    // Fungsi untuk mengupdate state berdasarkan input pengguna
    fun updateAsetState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    // Fungsi untuk memuat data Aset berdasarkan ID
    fun loadAsetById(idAset: Int) {
        viewModelScope.launch {
            try {
                val aset = asetRepository.getAsetById(idAset) // Dapatkan Aset langsung tanpa LiveData
                uiState = uiState.copy(updateUiEvent = aset.toUpdateUiEvent())
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.message ?: "Gagal memuat data Aset")
            }
        }
    }

    // Fungsi untuk menyimpan perubahan data Aset ke repository
    fun updateAset(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val aset = uiState.updateUiEvent.toAset()
                asetRepository.updateAset(aset.idAset, aset) // Panggil updateAset dengan id dan aset
                onSuccess() // Panggil callback saat berhasil
            } catch (e: Exception) {
                onError(e.message ?: "Gagal mengupdate Aset") // Panggil callback saat gagal
            }
        }
    }
}

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
