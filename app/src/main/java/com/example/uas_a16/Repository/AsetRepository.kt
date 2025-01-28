package com.example.uas_a16.Repository

import androidx.lifecycle.LiveData
import com.example.uas_a16.DAO.AsetDao
import com.example.uas_a16.model.Aset
class AsetRepository(private val asetDao: AsetDao) {

    // Mendapatkan seluruh data Aset
    val allAset: LiveData<List<Aset>> = asetDao.getAllAset()

    // Menambahkan Aset baru
    suspend fun insertAset(aset: Aset) {
        asetDao.insertAset(aset)
    }

    // Menghapus Aset
    suspend fun deleteAset(aset: Aset) {
        asetDao.deleteAset(aset)
    }

    // Mendapatkan Aset berdasarkan ID
    fun getAsetById(idAset: Int): LiveData<Aset> {
        return asetDao.getAsetById(idAset)
    }


    // Mengupdate Aset
    suspend fun updateAset(aset: Aset) {
        asetDao.updateAset(aset)
    }
}