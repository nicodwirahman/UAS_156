package com.example.uas_a16.Repository

import androidx.lifecycle.LiveData
import com.example.uas_a16.DAO.KategoriDao
import com.example.uas_a16.model.Kategori

class KategoriRepository(private val kategoriDao: KategoriDao) {

    // Mendapatkan seluruh data Kategori
    val allKategori: LiveData<List<Kategori>> = kategoriDao.getAllKategori()

    // Menambahkan Kategori baru
    suspend fun insertKategori(kategori: Kategori) {
        kategoriDao.insertKategori(kategori)
    }

    // Menghapus Kategori
    suspend fun deleteKategori(kategori: Kategori) {
        kategoriDao.deleteKategori(kategori)
    }

    // Memperbarui Kategori
    suspend fun updateKategori(kategori: Kategori) {
        kategoriDao.updateKategori(kategori)
    }
    // Mendapatkan Kategori berdasarkan ID
    fun getKategoriById(idKategori: Int): LiveData<Kategori> {
        return kategoriDao.getKategoriById(idKategori)
    }
}
