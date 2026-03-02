package ru.loginov.room.Daos;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import ru.loginov.room.DAO.models.Item;
import ru.loginov.room.DAO.models.Store;
import ru.loginov.room.DAO.models.shoppingList;

@Database(
    entities = [shoppingList::class,Item::class,Store::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDatabase: RoomDatabase(){
    abstract fun listDao():ListDao
    abstract fun itemDao():ItemDao
    abstract fun storeDao():StoreDao
}
