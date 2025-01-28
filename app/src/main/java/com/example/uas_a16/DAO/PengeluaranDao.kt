package com.example.uas_a16.DAO


import androidx.lifecycle.LiveData
import androidx.room.*
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

    // Mengambil data pengeluaran berdasarkan ID Aset
    @Query("SELECT * FROM pengeluaran WHERE idAset = :idAset")
    fun getPengeluaranByAset(idAset: Int): LiveData<List<Pengeluaran>>

    // Mengambil data pengeluaran berdasarkan ID Kategori
    @Query("SELECT * FROM pengeluaran WHERE idKategori = :idKategori")
    fun getPengeluaranByKategori(idKategori: Int): LiveData<List<Pengeluaran>>

    // Mengambil seluruh data pengeluaran
    @Query("SELECT * FROM pengeluaran")
    fun getAllPengeluaran(): LiveData<List<Pengeluaran>>

    // Menghitung total pengeluaran
    @Query("SELECT SUM(total) FROM pengeluaran")
    fun getTotalPengeluaran(): LiveData<Double>

    // Mengambil data pengeluaran berdasarkan ID
    @Query("SELECT * FROM pengeluaran WHERE idAset = :id")
    suspend fun getPengeluaranById(id: Int): Pengeluaran

    // Mengupdate data pengeluaran
    @Update
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran)
}