package ru.loginov.room.Daos;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


import ru.loginov.room.DAO.models.Item;
import ru.loginov.room.DAO.models.Store;
import ru.loginov.room.DAO.models.shoppingList;
import ru.loginov.room.converters.DateConverter

@Database(
    entities = [shoppingList::class,Item::class,Store::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ShoppingListDatabase: RoomDatabase(){
    abstract fun listDao():ListDao
    abstract fun itemDao():ItemDao
    abstract fun storeDao():StoreDao

    @OptIn(InternalCoroutinesApi::class)
    companion object{
        @Volatile
        var INSTANCE:ShoppingListDatabase? = null
        fun getDatabase(context: Context): ShoppingListDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ShoppingListDatabase::class.java,
                    "shopping_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
