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
        // Mengupdate data pengeluaran
    @Update
    suspend fun updatePengeluaran(pengeluaran: Pengeluaran)
}