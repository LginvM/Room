package ru.loginov.room.activitys


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.loginov.room.Daos.ItemWithStoreAndList
import ru.loginov.room.ui.Category
import ru.loginov.room.ui.Graph
import ru.loginov.room.ui.repository.Repository



class HomeViewModel(
    private val repository: Repository = Graph.repository
): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private fun getItems(){
        viewModelScope.launch{
            repository.getItemWithListAndStore.collectLatest {

            }
        }
    }

}

data class HomeState(
    val items:List<ItemWithStoreAndList> = emptyList(),
    val category: Category = Category(),
    val itemChecked: Boolean = false
)