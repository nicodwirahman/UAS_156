package com.example.uas_a16.ui.ViewModel.Keuangan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.Repository.PengeluaranRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinanceViewModel(
    private val pendapatanRepository: PendapatanRepository,
    private val pengeluaranRepository: PengeluaranRepository
) : ViewModel() {
    private val _saldo = MutableStateFlow(0.0)
    val saldo: StateFlow<Double> = _saldo

    private val _totalPemasukan = MutableStateFlow(0.0)
    val totalPemasukan: StateFlow<Double> = _totalPemasukan

    private val _totalPengeluaran = MutableStateFlow(0.0)
    val totalPengeluaran: StateFlow<Double> = _totalPengeluaran

    init {
        loadFinanceData()
    }

    private fun loadFinanceData() {
        viewModelScope.launch {
            val pemasukan = pendapatanRepository.getAllPendapatan().sumOf { it.total }
            val pengeluaran = pengeluaranRepository.getAllPengeluaran().sumOf { it.total }
            _totalPemasukan.value = pemasukan
            _totalPengeluaran.value = pengeluaran
            _saldo.value = pemasukan - pengeluaran
        }
    }
}