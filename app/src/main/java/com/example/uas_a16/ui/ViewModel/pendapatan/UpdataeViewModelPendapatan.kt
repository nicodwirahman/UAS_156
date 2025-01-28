package com.example.uas_a16.ui.ViewModel.pendapatan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import kotlinx.coroutines.launch

// Sealed class untuk state UI
sealed class UpdatePendapatanUiState {
    object Success : UpdatePendapatanUiState()
    object Error : UpdatePendapatanUiState()
    object Loading : UpdatePendapatanUiState()
}

// ViewModel untuk mengelola update pendapatan
class UpdatePendapatanViewModel(private val pendapatanRepository: PendapatanRepository) : ViewModel() {

    var updatePendapatanUiState = mutableStateOf<UpdatePendapatanUiState>(UpdatePendapatanUiState.Loading)
        private set

    var insertUiState = InsertPendapatanUiState()
        private set

    fun updateInsertPendapatanState(event: InsertPendapatanEvent) {
        insertUiState = insertUiState.copy(insertPendapatanEvent = event)
    }

    fun updatePendapatan() {
        viewModelScope.launch {
            updatePendapatanUiState.value = UpdatePendapatanUiState.Loading
            try {
                // Mengambil data pendapatan dari InsertPendapatanEvent dan mengubahnya menjadi objek Pendapatan
                val pendapatan = insertUiState.insertPendapatanEvent.toPendapatan()

                // Menggunakan repository untuk memperbarui pendapatan
                pendapatanRepository.updatePendapatan(pendapatan.idAset, pendapatan)

                updatePendapatanUiState.value = UpdatePendapatanUiState.Success
            } catch (e: Exception) {
                updatePendapatanUiState.value = UpdatePendapatanUiState.Error
            }
        }
    }
}
