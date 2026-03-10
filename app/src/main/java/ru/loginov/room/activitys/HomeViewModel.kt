package ru.loginov.room.activitys


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.loginov.room.DAO.models.Item
import ru.loginov.room.DAO.models.shoppingList
import ru.loginov.room.Daos.ItemWithStoreAndList
import ru.loginov.room.ui.Category
import ru.loginov.room.ui.Graph
import ru.loginov.room.ui.repository.Repository



class HomeViewModel(
    private val repository: Repository = Graph.repository
): ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch{
            repository.getItemWithListAndStore.collectLatest {
                //collectLatest — это терминальный suspending-оператор из kotlinx.coroutines.flow, который для каждого нового значения потока запускает заданный блок кода,
                // и если пока этот блок ещё выполняется приходит новое значение, он отменяет (прервывает) текущий блок и запускает его заново для этого нового значения.
                // То есть всегда выполняется только обработка последнего пришедшего элемента.

                state = state.copy(
                    items = it
                )
            }
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch{
            repository.deleteItem(item)
        }
    }

    fun onCategoryChange(category: Category){
        state=state.copy(category = category)
        filterBy(category.id)
    }

    private fun filterBy(shoppingListId:Int){
        if (shoppingListId!= 10001){
            viewModelScope.launch {
                repository.getItemWithStoreAndListFiltered(
                    shoppingListId
                ).collectLatest {
                    state = state.copy(items = it)
                }
            }
        }else{
            getItems()
        }
    }

}

data class HomeState(
    val items:List<ItemWithStoreAndList> = emptyList(),
    val category: Category,
    val itemChecked: Boolean = false
)