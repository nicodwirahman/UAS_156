package com.example.uas_a16.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uas_a16.model.Kategori
import retrofit2.http.Headers

@Dao
interface KategoriDao {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKategori(kategori: Kategori)
    @Delete
    suspend fun deleteKategori(kategori: Kategori)

    @Update
    suspend fun updateKategori(kategori: Kategori)



}
