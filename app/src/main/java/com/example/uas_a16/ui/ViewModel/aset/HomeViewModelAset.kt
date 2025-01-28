
import com.example.uas_a16.model.Aset


sealed class HomeAsetUiState {
    data class Success(val aset: List<Aset>) : HomeAsetUiState()
    object Error : HomeAsetUiState()
    object Loading : HomeAsetUiState()
}

