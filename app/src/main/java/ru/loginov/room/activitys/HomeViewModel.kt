package ru.loginov.room.activitys

import androidx.lifecycle.ViewModel
import ru.loginov.room.Daos.ItemWithStoreAndList
import ru.loginov.room.ui.Graph
import ru.loginov.room.ui.repository.Repository


class HomeViewModel(
    private val repository: Repository = Graph.repository
): ViewModel() {

}

data class HomeState(
    val items:List<ItemWithStoreAndList> = emptyList()
    val category: Category = Category()
)