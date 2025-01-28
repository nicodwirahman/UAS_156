package com.example.uas_a16.Repository


import com.example.uas_a16.di.ApiService
import com.example.uas_a16.model.Aset
interface AsetRepository {
    suspend fun getAllAset(): List<Aset>
    suspend fun insertAset(aset: Aset)
    suspend fun updateAset(id: Int, aset: Aset)
    suspend fun deleteAset(id: Int)
    suspend fun getAsetById(id: Int): Aset
}

class NetworkAsetRepository(private val apiService: ApiService) : AsetRepository {
    override suspend fun getAllAset(): List<Aset> {
        return apiService.getAllAset()
    }

    override suspend fun insertAset(aset: Aset) {
        apiService.insertAset(aset)
    }

    override suspend fun updateAset(id: Int, aset: Aset) {
        apiService.updateAset(id, aset)
    }

    override suspend fun deleteAset(id: Int) {
        apiService.deleteAset(id)
    }

    override suspend fun getAsetById(id: Int): Aset {
        return apiService.getAsetById(id)
    }
}
