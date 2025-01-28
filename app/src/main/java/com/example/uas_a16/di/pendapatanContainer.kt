import com.example.uas_a16.DAO.AsetDao
import com.example.uas_a16.DAO.KategoriDao
import com.example.uas_a16.DAO.PendapatanDao
import com.example.uas_a16.DAO.PengeluaranDao
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.Repository.KategoriRepository
import com.example.uas_a16.Repository.PendapatanRepository
import com.example.uas_a16.Repository.PengeluaranRepository
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

    // Aset
    private val asetDao: AsetDao by lazy {
        retrofit.create(AsetDao::class.java)
    }
    override val asetRepository: AsetRepository by lazy {
        AsetRepository(asetDao)
    }

    // Pendapatan
    private val pendapatanDao: PendapatanDao by lazy {
        retrofit.create(PendapatanDao::class.java)
    }
    override val pendapatanRepository: PendapatanRepository by lazy {
        PendapatanRepository(pendapatanDao)
    }

    // Pengeluaran
    private val pengeluaranDao: PengeluaranDao by lazy {
        retrofit.create(PengeluaranDao::class.java)
    }
    override val pengeluaranRepository: PengeluaranRepository by lazy {
        PengeluaranRepository(pengeluaranDao)
    }

    // Kategori
    private val kategoriDao: KategoriDao by lazy {
        retrofit.create(KategoriDao::class.java)
    }
    override val kategoriRepository: KategoriRepository by lazy {
        KategoriRepository(kategoriDao)
    }
}
