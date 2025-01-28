package com.example.uas_a16.DAO


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.model.Pengeluaran
import retrofit2.http.Headers


@Dao
interface PengeluaranDao {


    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    // Menambahkan data pengeluaran
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPengeluaran(pengeluaran: Pengeluaran)

    // Menghapus data pengeluaran
    @Delete
    suspend fun deletePengeluaran(pengeluaran: Pengeluaran)
        // Mengupdate data pengeluaran
    @Update
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran)


    // Mengambil pendapatan berdasarkan ID Aset
    @Query("SELECT * FROM pendapatan WHERE idAset = :idAset")
    fun getPendapatanByAset(idAset: Int): LiveData<List<Pendapatan>>

    // Mengambil pendapatan berdasarkan ID Kategori
    @Query("SELECT * FROM pendapatan WHERE idKategori = :idKategori")
    fun getPendapatanByKategori(idKategori: Int): LiveData<List<Pendapatan>>

    // Mengambil seluruh data pendapatan
    @Query("SELECT * FROM pendapatan")
    fun getAllPendapatan(): LiveData<List<Pendapatan>>

    // Menghitung total pendapatan
    @Query("SELECT SUM(total) FROM pendapatan")
    fun getTotalPendapatan(): LiveData<Double>
}