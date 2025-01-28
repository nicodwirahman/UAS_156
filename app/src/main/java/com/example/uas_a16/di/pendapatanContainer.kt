
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.Repository.KategoriRepository
import com.example.uas_a16.Repository.NetworkAsetRepository
import com.example.uas_a16.Repository.NetworkKategoriRepository
import com.example.uas_a16.Repository.NetworkPendapatanRepository
import com.example.uas_a16.Repository.NetworkPengeluaranRepository
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.Repository.PengeluaranRepository
import com.example.uas_a16.di.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val pendapatanRepository: PendapatanRepository
    val asetRepository: AsetRepository
    val pengeluaranRepository: PengeluaranRepository
    val kategoriRepository: KategoriRepository
}

class PendapatanContainer : AppContainer {
    private val baseUrl = "http://10.2.2:3000/api/" // Sesuaikan baseUrl sesuai kebutuhan
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Aset
    override val asetRepository: AsetRepository by lazy {
        NetworkAsetRepository(apiService)
    }

    // Pendapatan
    override val pendapatanRepository: PendapatanRepository by lazy {
        NetworkPendapatanRepository(apiService)
    }

    // Pengeluaran
    override val pengeluaranRepository: PengeluaranRepository by lazy {
        NetworkPengeluaranRepository(apiService)
    }

    // Kategori
    override val kategoriRepository: KategoriRepository by lazy {
        NetworkKategoriRepository(apiService)
    }
}
