package ru.loginov.room.ui

import androidx.annotation.DrawableRes
import ru.loginov.room.R

object Utils{
    val category = listOf(
        Category(title= "Drinks",resId = R.drawable.ic_launcher_background,id = 0),
        Category(title= "Vegetable",resId = R.drawable.ic_launcher_background,id = 1),
        Category(title= "Fruits",resId = R.drawable.ic_launcher_background,id = 2),
        Category(title= "Cleaning",resId = R.drawable.ic_launcher_background,id = 3),
        Category(title= "Electronic",resId = R.drawable.ic_launcher_background,id = 4) ,
        Category(title= "None",resId = R.drawable.ic_launcher_background,id = 5)
    )
}

data class Category(
    @DrawableRes val resId: Int = -1,
    val title:String = "",
    val id: Int = -1,
)
