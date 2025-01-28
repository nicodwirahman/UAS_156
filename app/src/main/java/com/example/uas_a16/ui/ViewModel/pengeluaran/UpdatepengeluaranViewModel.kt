package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.ui.navigasi.DestinasiUpdate


class UpdatePengeluaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pengeluaranRepository: PengeluaranRepository
) : ViewModel() {
    var updateUiState by mutableStateOf(InsertPengeluaranUiState())
        private set

    private val _Aset: Int =
        checkNotNull(savedStateHandle[DestinasiUpdate.route]) // Memastikan ID pengeluaran ada di SavedState

    init {

        pengeluaranRepository.getPengeluaranByAset(_Aset).observeForever { pengeluaranList ->

            pengeluaranList?.firstOrNull()?.let { pengeluaran ->
                updateUiState = pengeluaran.toUiStatePengeluaran()
            }
        }
    }
}



