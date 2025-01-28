package com.example.uas_a16.dependenciesinjection

import com.example.uas_a16.Repository.KeuanganRepository
import com.example.uas_a16.Repository.NetworkKeuanganRepository
import com.example.uas_a16.service_api.KeuanganService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val keuanganRepository: KeuanganRepository
}

class KeuanganContainer : AppContainer {
    private val baseUrl = "http://10.2.2:3000/api/keuangan/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val keuanganService: KeuanganService by lazy {
        retrofit.create(KeuanganService::class.java)
    }

    override val keuanganRepository: KeuanganRepository by lazy {
        NetworkKeuanganRepository(keuanganService)
    }
}
