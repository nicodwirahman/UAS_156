package com.example.uas_a16.Repository


import com.example.uas_a16.di.ApiService
import com.example.uas_a16.model.Pengeluaran


interface PengeluaranRepository {
    suspend fun getAllPengeluaran(): List<Pengeluaran>
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran)
    suspend fun updatePengeluaran(id: Int, pengeluaran: Pengeluaran)
    suspend fun deletePengeluaran(id: Int)
    suspend fun getPengeluaranById(id: Int): Pengeluaran
}

class NetworkPengeluaranRepository(private val apiService: ApiService) : PengeluaranRepository {
    override suspend fun getAllPengeluaran(): List<Pengeluaran> {
        return apiService.getAllPengeluaran()
    }

    override suspend fun insertPengeluaran(pengeluaran: Pengeluaran) {
        apiService.insertPengeluaran(pengeluaran)
    }

    override suspend fun updatePengeluaran(id: Int, pengeluaran: Pengeluaran) {
        apiService.updatePengeluaran(id, pengeluaran)
    }

    override suspend fun deletePengeluaran(id: Int) {
        apiService.deletePengeluaran(id)
    }

    override suspend fun getPengeluaranById(id: Int): Pengeluaran {
        return apiService.getPengeluaranById(id)
    }
}
