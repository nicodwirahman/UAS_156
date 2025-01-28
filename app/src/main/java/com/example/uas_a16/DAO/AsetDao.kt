package com.example.uas_a16.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uas_a16.model.Aset
import retrofit2.http.Headers

@Dao
interface AsetDao {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    // Mendapatkan seluruh data Aset
    @Query("SELECT * FROM aset")
    fun getAllAset(): LiveData<List<Aset>>

    // Menambahkan Aset baru
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAset(aset: Aset)

    // Menghapus Aset
    @Delete
    suspend fun deleteAset(aset: Aset)
    // Mendapatkan Aset berdasarkan ID
    @Query("SELECT * FROM aset WHERE idAset = :idAset")
    fun getAsetById(idAset: Int): LiveData<Aset>


    // Mengupdate Aset
    @Update
    suspend fun updateAset(aset: Aset)

}
