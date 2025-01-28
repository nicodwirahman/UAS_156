package com.example.uas_a16.Repository

import androidx.lifecycle.LiveData
import com.example.uas_a16.DAO.KeuanganDao
import com.example.uas_a16.DAO.PendapatanDao
import com.example.uas_a16.DAO.PengeluaranDao
import com.example.uas_a16.model.Keuangan
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.model.Pengeluaran
class RepositoryKeuangan(
    private val pengeluaranDao: PengeluaranDao,
    private val pendapatanDao: PendapatanDao
) {

    // Pengeluaran
    fun getAllPengeluaran(): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getAllPengeluaran()
    }

    fun getPengeluaranByAset(idAset: Int): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getPengeluaranByAset(idAset)
    }

    fun getPengeluaranByKategori(idKategori: Int): LiveData<List<Pengeluaran>> {
        return pengeluaranDao.getPengeluaranByKategori(idKategori)
    }

    suspend fun insertPengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.insertPengeluaran(pengeluaran)
    }

    suspend fun deletePengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.deletePengeluaran(pengeluaran)
    }

    fun getTotalPengeluaran(): LiveData<Double> {
        return pengeluaranDao.getTotalPengeluaran()
    }

    suspend fun getPengeluaranById(id: Int): Pengeluaran {
        return pengeluaranDao.getPengeluaranById(id)
    }

    suspend fun updatePengeluaran(pengeluaran: Pengeluaran) {
        pengeluaranDao.updatePengeluaran(pengeluaran)
    }

    // Pendapatan (tambahkan jika diperlukan)
    // Contoh implementasi pendapatan
    fun getTotalPendapatan(): LiveData<Double> {
        return pendapatanDao.getTotalPendapatan()
    }
}
