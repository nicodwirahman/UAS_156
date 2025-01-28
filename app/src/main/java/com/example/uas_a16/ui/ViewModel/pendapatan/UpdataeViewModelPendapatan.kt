package com.example.uas_a16.ui.ViewModel.pendapatan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uas_a16.Repository.PendapatanRepository

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
}
