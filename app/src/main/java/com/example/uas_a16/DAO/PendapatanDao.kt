package com.example.uas_a16.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.model.Pengeluaran
import retrofit2.http.Headers

@Dao
interface PendapatanDao {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    // Menambahkan data pendapatan
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendapatan(pendapatan: Pendapatan)


}
