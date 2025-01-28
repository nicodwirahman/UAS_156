import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_a16.Repository.AsetRepository
import com.example.uas_a16.model.Aset
import kotlinx.coroutines.launch

sealed class HomeAsetUiState {
    data class Success(val aset: List<Aset>) : HomeAsetUiState()
    object Error : HomeAsetUiState()
    object Loading : HomeAsetUiState()
}


class HomeAsetViewModel(private val asetRepository: AsetRepository) : ViewModel() {
    var asetUiState: HomeAsetUiState by mutableStateOf(HomeAsetUiState.Loading)
        private set

    init {
        getAset()
    }

    // Mendapatkan list Aset
    fun getAset() {
        viewModelScope.launch {
            asetUiState = HomeAsetUiState.Loading
            asetUiState = try {
                // Ambil list aset dari repository menggunakan getAllAset()
                val asetList = asetRepository.getAllAset() // Ganti dengan fungsi yang benar
                HomeAsetUiState.Success(asetList)
            } catch (e: Exception) {
                HomeAsetUiState.Error
            }
        }
    }

    // Menghapus Aset
    fun deleteAset(aset: Aset) {
        viewModelScope.launch {
            try {
                // Hapus aset dengan idAset
                asetRepository.deleteAset(aset.idAset)
            } catch (e: Exception) {
                asetUiState = HomeAsetUiState.Error
            }
        }
    }
}
