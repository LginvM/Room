package ru.loginov.room.DAO.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "shopping_list")
data class shoppingList(
    @ColumnInfo(name = "list_id")
    @PrimaryKey
    val id:Int,
    val name:String
)
@Entity(tableName = "items")
data class Item(
    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName : String,
//    val category:String, //я ваще хз, но в видосе нет этого
    val qty: String,
    val listId:Int,
    val storeIdFk: Int,
    val date: Date,
    val isChecked: Boolean
)

@Entity(tableName = "stores")
data class Store(
    @ColumnInfo(name = "store_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listIdFk : Int
)
