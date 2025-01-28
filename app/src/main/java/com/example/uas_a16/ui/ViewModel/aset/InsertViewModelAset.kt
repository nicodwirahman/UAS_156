package com.example.uas_a16.ui.ViewModel.aset



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.model.Aset
class InsertAsetViewModel(private val asetRepository: AsetRepository) : ViewModel() {
    // State untuk menyimpan data input Aset
    var uiState by mutableStateOf(InsertUiState())
        private set

    // Fungsi untuk mengupdate state berdasarkan input pengguna
    fun updateInsertAsetState(insertUiEvent: InsertUiEvent) {
        uiState = uiState.copy(insertUiEvent = insertUiEvent)
    }

    // Fungsi untuk menyimpan data Aset ke repository
    fun insertAset() {
        viewModelScope.launch {
            try {
                asetRepository.insertAset(uiState.insertUiEvent.toAset())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

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