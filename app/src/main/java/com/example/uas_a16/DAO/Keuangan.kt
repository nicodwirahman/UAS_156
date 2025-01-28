package com.example.uas_a16.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.model.Pengeluaran
import retrofit2.http.Headers

@Dao
interface KeuanganDao {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    // Manajemen Pendapatan
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendapatan(pendapatan: Pendapatan)

    @Update
    suspend fun updatePendapatan(pendapatan: Pendapatan)

    @Delete
    suspend fun deletePendapatan(pendapatan: Pendapatan)

    @Query("SELECT * FROM pendapatan WHERE idAset = :idAset")
    fun getPendapatanByAset(idAset: Int): LiveData<List<Pendapatan>>

    @Query("SELECT * FROM pendapatan WHERE idKategori = :idKategori")
    fun getPendapatanByKategori(idKategori: Int): LiveData<List<Pendapatan>>

    @Query("SELECT * FROM pendapatan")
    fun getAllPendapatan(): LiveData<List<Pendapatan>>

    @Query("SELECT SUM(total) FROM pendapatan")
    fun getTotalPendapatan(): LiveData<Double>

    // Manajemen Pengeluaran
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran)

    @Update
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran)

    @Delete
    suspend fun deletePengeluaran(pengeluaran: Pengeluaran)

    @Query("SELECT * FROM pengeluaran WHERE idAset = :idAset")
    fun getPengeluaranByAset(idAset: Int): LiveData<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran WHERE idKategori = :idKategori")
    fun getPengeluaranByKategori(idKategori: Int): LiveData<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran")
    fun getAllPengeluaran(): LiveData<List<Pengeluaran>>

    @Query("SELECT SUM(total) FROM pengeluaran")
    fun getTotalPengeluaran(): LiveData<Double>

    // Menampilkan Saldo (Total Pendapatan - Total Pengeluaran)
    @Query("""
        SELECT 
        (SELECT IFNULL(SUM(total), 0) FROM pendapatan) - 
        (SELECT IFNULL(SUM(total), 0) FROM pengeluaran) AS saldo
    """)
    fun getSaldo(): LiveData<Double>
}
