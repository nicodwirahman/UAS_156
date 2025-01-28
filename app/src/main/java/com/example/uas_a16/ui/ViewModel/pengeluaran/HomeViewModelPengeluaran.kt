package com.example.uas_a16.ui.ViewModel.pengeluaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.model.Pengeluaran
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePengeluaranUiState {
    data class Success(val pengeluaran: List<Pengeluaran>) : HomePengeluaranUiState()
    object Error : HomePengeluaranUiState()
    object Loading : HomePengeluaranUiState()
}