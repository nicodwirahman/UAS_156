package com.example.uas_a16.ui.ViewModel.Keuangan


import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.RepositoryKeuangan
import kotlinx.coroutines.launch

class HomePengeluaranViewModel(private val repositoryKeuangan: RepositoryKeuangan) : ViewModel() {

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> get() = _saldo

    private val _totalPengeluaran = MutableLiveData<Double>()
    val totalPengeluaran: LiveData<Double> get() = _totalPengeluaran

    private val _totalPendapatan = MutableLiveData<Double>()
    val totalPendapatan: LiveData<Double> get() = _totalPendapatan

    private val _saldoColor = MutableLiveData<Color>()
    val saldoColor: LiveData<Color> get() = _saldoColor

    init {
        fetchKeuanganData()
    }

    private fun fetchKeuanganData() {
        viewModelScope.launch {
            // Ambil total pendapatan dan pengeluaran dari repository
            val pendapatan = repositoryKeuangan.getTotalPendapatan().value ?: 0.0
            val pengeluaran = repositoryKeuangan.getTotalPengeluaran().value ?: 0.0

            // Hitung saldo
            val calculatedSaldo = pendapatan - pengeluaran

            // Update LiveData
            _totalPendapatan.postValue(pendapatan)
            _totalPengeluaran.postValue(pengeluaran)
            _saldo.postValue(calculatedSaldo)

            // Tentukan warna saldo berdasarkan besar kecilnya nilai
            val color = if (calculatedSaldo >= 0) {
                Color.Green // Warna hijau jika saldo positif
            } else {
                Color.Red // Warna merah jika saldo negatif
            }
            _saldoColor.postValue(color)
        }
    }
}
