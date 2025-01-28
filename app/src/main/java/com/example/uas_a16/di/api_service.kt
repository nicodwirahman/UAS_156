package com.example.uas_a16.di

import com.example.uas_a16.model.Aset
import com.example.uas_a16.model.Kategori
import com.example.uas_a16.model.Pendapatan
import com.example.uas_a16.model.Pengeluaran
import com.google.android.gms.common.api.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    // Aset Endpoints
    @POST("aset")
    suspend fun insertAset(@Body aset: Aset): Response<Aset>

    @GET("aset")
    suspend fun getAllAset(): Response<List<Aset>>

    @GET("aset/{id}")
    suspend fun getAsetById(@Path("id") id: Int): Response<Aset>

    @PUT("aset/{id}")
    suspend fun updateAset(@Path("id") id: Int, @Body aset: Aset): Response<Aset>

    @DELETE("aset/{id}")
    suspend fun deleteAset(@Path("id") id: Int): Response<Void>

    // Kategori Endpoints
    @POST("kategori")
    suspend fun insertKategori(@Body kategori: Kategori): Response<Kategori>

    @GET("kategori")
    suspend fun getAllKategori(): Response<List<Kategori>>

    @GET("kategori/{id}")
    suspend fun getKategoriById(@Path("id") id: Int): Response<Kategori>

    @PUT("kategori/{id}")
    suspend fun updateKategori(@Path("id") id: Int, @Body kategori: Kategori): Response<Kategori>

    @DELETE("kategori/{id}")
    suspend fun deleteKategori(@Path("id") id: Int): Response<Void>

    // Pengeluaran Endpoints
    @POST("pengeluaran")
    suspend fun insertPengeluaran(@Body pengeluaran: Pengeluaran): Response<Pengeluaran>

    @GET("pengeluaran")
    suspend fun getAllPengeluaran(): Response<List<Pengeluaran>>

    @GET("pengeluaran/{id}")
    suspend fun getPengeluaranById(@Path("id") id: Int): Response<Pengeluaran>

    @PUT("pengeluaran/{id}")
    suspend fun updatePengeluaran(@Path("id") id: Int, @Body pengeluaran: Pengeluaran): Response<Pengeluaran>

    @DELETE("pengeluaran/{id}")
    suspend fun deletePengeluaran(@Path("id") id: Int): Response<Void>

    // Pendapatan Endpoints
    @POST("pendapatan")
    suspend fun insertPendapatan(@Body pendapatan: Pendapatan): Response<Pendapatan>

    @GET("pendapatan")
    suspend fun getAllPendapatan(): Response<List<Pendapatan>>

    @GET("pendapatan/{id}")
    suspend fun getPendapatanById(@Path("id") id: Int): Response<Pendapatan>

    @PUT("pendapatan/{id}")
    suspend fun updatePendapatan(@Path("id") id: Int, @Body pendapatan: Pendapatan): Response<Pendapatan>

    @DELETE("pendapatan/{id}")
    suspend fun deletePendapatan(@Path("id") id: Int): Response<Void>
}
