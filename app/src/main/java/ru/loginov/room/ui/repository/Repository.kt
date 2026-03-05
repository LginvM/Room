package ru.loginov.room.ui.repository

import ru.loginov.room.DAO.models.Item
import ru.loginov.room.DAO.models.Store
import ru.loginov.room.DAO.models.shoppingList
import ru.loginov.room.Daos.ItemDao
import ru.loginov.room.Daos.ListDao
import ru.loginov.room.Daos.StoreDao

class Repository(
    private val listDao: ListDao,
    private val storeDao: StoreDao,
    private val itemDao: ItemDao
) {
    val store = storeDao.getAllStore()
    val getItemWithListAndStore = listDao.getItemsWithStoreAndList()

    fun getItemWithStoreAndList(id:Int) = listDao
        .getItemWithStoreAndListFiltered(id)

    fun getItemWithStoreAndListFiltered (id:Int) = listDao
        .getItemsWithStoreAndListFiltered(id)


    suspend fun insertList(shoppingList: shoppingList){
        listDao.insertShoppingList(shoppingList)
    }

    suspend fun insertStore(store: Store){
        storeDao.insert(store)
    }

    suspend fun insertItem(item: Item){
        itemDao.insert(item)
    }

    suspend fun deleteItem(item: Item){
        itemDao.delete(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.update(item)
    }
}