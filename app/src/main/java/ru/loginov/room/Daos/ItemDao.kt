package ru.loginov.room.Daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.loginov.room.DAO.models.Item
import ru.loginov.room.DAO.models.Store
import ru.loginov.room.DAO.models.shoppingList

@Dao
interface ItemDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE) //при одинаковом id заменять на новый элемент
    suspend fun insert(item: Item)

    @Update(onConflict = OnConflictStrategy.REPLACE )
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items")
    fun getAlllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE item_id =:itemId")
    fun getItems(itemId:Int):Flow<Item>

}

@Dao
interface StoreDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE) //при одинаковом id заменять на новый элемент
    suspend fun insert(store: Store)

    @Update(onConflict = OnConflictStrategy.REPLACE )
    suspend fun update(store: Store)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM stores")
    fun getAlllStore(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE store_id =:storeId")
    fun getStore(storeId:Int):Flow<Store>
}

@Dao
interface ListDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: shoppingList)

    @Query(
        """
        SELECT * FROM items AS I INNER JOIN shopping_list AS S
        ON I.listId = S.list_id INNER JOIN stores AS ST
        ON I.storeIdFk = ST.store_id WHERE ST.listIdFk =:listId
    """
    )
    fun getItemsWithStoreAndListFiltered(listId:Int)
            :Flow<List<ItemWithStoreAndList>>



    @Query(
        """
        SELECT * FROM items AS I INNER JOIN shopping_list AS S
        ON I.listId = S.list_id INNER JOIN stores AS ST
        ON I.storeIdFk = ST.store_id WHERE I.item_id =:itemId
    """
    )
    fun getItemWithStoreAndListFiltered(itemId:Int)
            :Flow<ItemWithStoreAndList>
}

data class ItemWithStoreAndList(
    @Embedded val item: Item,
    @Embedded val shoppingList: shoppingList,
    @Embedded val store: Store
)