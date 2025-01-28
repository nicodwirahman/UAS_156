package com.example.uas_a16.Repository

import com.example.uas_a16.di.ApiService
import com.example.uas_a16.model.Kategori

interface KategoriRepository {
    suspend fun getAllKategori(): List<Kategori>
    suspend fun insertKategori(kategori: Kategori)
    suspend fun updateKategori(id: Int, kategori: Kategori)
    suspend fun deleteKategori(id: Int)
    suspend fun getKategoriById(id: Int): Kategori
}

class NetworkKategoriRepository(private val apiService: ApiService) : KategoriRepository {
    override suspend fun getAllKategori(): List<Kategori> {
        return apiService.getAllKategori()
    }

    override suspend fun insertKategori(kategori: Kategori) {
        apiService.insertKategori(kategori)
    }

    override suspend fun updateKategori(id: Int, kategori: Kategori) {
        apiService.updateKategori(id, kategori)
    }

    override suspend fun deleteKategori(id: Int) {
        apiService.deleteKategori(id)
    }

    override suspend fun getKategoriById(id: Int): Kategori {
        return apiService.getKategoriById(id)
    }
}
