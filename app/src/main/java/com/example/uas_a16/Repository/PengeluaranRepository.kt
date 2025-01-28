package com.example.uas_a16.Repository

import androidx.lifecycle.LiveData
import com.example.uas_a16.DAO.PengeluaranDao
import com.example.uas_a16.model.Pengeluaran


class PengeluaranRepository(private val pengeluaranDao: PengeluaranDao) {

    // Mengambil seluruh data pengeluaran
    fun getAllPengeluaran(): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getAllPengeluaran()
    }

    // Mengambil data pengeluaran berdasarkan Aset
    fun getPengeluaranByAset(idAset: Int): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getPengeluaranByAset(idAset)
    }

    // Mengambil data pengeluaran berdasarkan Kategori
    fun getPengeluaranByKategori(idKategori: Int): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getPengeluaranByKategori(idKategori)
    }

    // Menambah pengeluaran baru
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.insertPengeluaran(pengeluaran)
    }

    // Menghapus pengeluaran berdasarkan objek Pengeluaran
    suspend fun deletePengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.deletePengeluaran(pengeluaran)
    }

    // Menghitung total pengeluaran
    fun getTotalPengeluaran(): LiveData<Double> {
        return pengeluaranDao.getTotalPengeluaran()
    }

    // Mengambil pengeluaran berdasarkan ID
    suspend fun getPengeluaranById(idAset: Int): Pengeluaran {
        return pengeluaranDao.getPengeluaranById(idAset)
    }    // Mengupdate data pengeluaran
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.updatePengeluaran(pengeluaran)
    }
}