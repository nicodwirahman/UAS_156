package com.example.uas_a16.Repository

import androidx.lifecycle.LiveData
import com.example.uas_a16.DAO.PendapatanDao
import com.example.uas_a16.model.Pendapatan

class PendapatanRepository(private val pendapatanDao: PendapatanDao) {

    // Mengambil seluruh data pendapatan
    fun getAllPendapatan(): LiveData<List<Pendapatan>> {
        return pendapatanDao.getAllPendapatan()
    }

    // Mengambil data pendapatan berdasarkan Aset
    fun getPendapatanByAset(idAset: Int): LiveData<List<Pendapatan>> {
        return pendapatanDao.getPendapatanByAset(idAset)
    }

    // Mengambil data pendapatan berdasarkan Kategori
    fun getPendapatanByKategori(idKategori: Int): LiveData<List<Pendapatan>> {
        return pendapatanDao.getPendapatanByKategori(idKategori)
    }

    // Menambah pendapatan baru
    suspend fun insertPendapatan(pendapatan: Pendapatan) {
        pendapatanDao.insertPendapatan(pendapatan)
    }

    // Menghapus pendapatan
    suspend fun deletePendapatan(pendapatan: Pendapatan) {
        pendapatanDao.deletePendapatan(pendapatan)
    }

    // Menghitung total pendapatan
    fun getTotalPendapatan(): LiveData<Double> {
        return pendapatanDao.getTotalPendapatan()
    }

    // Mengupdate pendapatan
    suspend fun updatePendapatan(idAset: Int, total: Double) {
        pendapatanDao.updatePendapatan(idAset, total)
    }
}