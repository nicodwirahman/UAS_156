package com.example.uas_a16.Repository

import com.example.uas_a16.di.ApiService
import com.example.uas_a16.model.Pendapatan

interface PendapatanRepository {
    suspend fun getAllPendapatan(): List<Pendapatan>
    suspend fun insertPendapatan(pendapatan: Pendapatan)
    suspend fun updatePendapatan(id: Int, pendapatan: Pendapatan)
    suspend fun deletePendapatan(id: Int)
    suspend fun getPendapatanById(id: Int): Pendapatan
}

class NetworkPendapatanRepository(private val apiService: ApiService) : PendapatanRepository {
    override suspend fun getAllPendapatan(): List<Pendapatan> {
        return apiService.getAllPendapatan()
    }

    override suspend fun insertPendapatan(pendapatan: Pendapatan) {
        apiService.insertPendapatan(pendapatan)
    }

    override suspend fun updatePendapatan(id: Int, pendapatan: Pendapatan) {
        apiService.updatePendapatan(id, pendapatan)
    }

    override suspend fun deletePendapatan(id: Int) {
        apiService.deletePendapatan(id)
    }

    override suspend fun getPendapatanById(id: Int): Pendapatan {
        return apiService.getPendapatanById(id)
    }
}
