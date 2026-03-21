package ru.loginov.room.activitys

import ads_mobile_sdk.h6
import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.loginov.room.DAO.models.Item
import ru.loginov.room.Daos.ItemWithStoreAndList
import ru.loginov.room.ui.Category
import ru.loginov.room.ui.Utils
import ru.loginov.room.ui.theme.Shapes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onNavigate:(Int) -> Unit
) {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = homeViewModel.state
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {onNavigate.invoke(-1)}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null,)
            }
        }

    ) {
        LazyColumn {
            item {
                LazyRow {
                    items(Utils.category){category: Category ->
                        CategoryItem(iconRes = category.resId, title = category.title, selected = category==homeState.category) {
                            homeViewModel.onCategoryChange(category)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
            items(homeState.items){
                ShoppingItems(item = it,
                    isChecked = it.item.isChecked,
                    onCheckedChange = homeViewModel::onItemCheckedChange) {
                    onNavigate.invoke(it.item.id)
                }
            }
        }


    }
}

@Composable
fun CategoryItem(
    @DrawableRes iconRes:Int,
    title:String,
    selected:Boolean,
    onItemClick:() -> Unit
){
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
            .selectable(
                selected = selected,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.Blue),
                onClick = { onItemClick.invoke() }
            ),

        border = BorderStroke(
            1.dp,
            if (selected) MaterialTheme.colorScheme.primary.copy(.5f)
            else MaterialTheme.colorScheme.onSurface
        ),
        shape = Shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
//            colors = if(selected) MaterialTheme.colorScheme.primary.copy(.5f) else MaterialTheme.colorScheme.onSurface,
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically, //add
            modifier = Modifier.padding(8.dp)
        ){
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium, )//add
        }
    }


}

@Composable
fun ShoppingItems(
    modifier: Modifier = Modifier,
    item: ItemWithStoreAndList,
    isChecked:Boolean,
    onCheckedChange:(Item, Boolean) -> Unit,
    onItemClick: () -> Unit
    ) {
    Card(
        Modifier
            .fillMaxWidth()
            .clickable{
                onItemClick.invoke()
            }.padding(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Column(
                Modifier.padding(8.dp)
            ) {
                Text(text = item.item.itemName,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(4.dp))
                Text(item.store.storeName)
                Spacer(Modifier.size(4.dp))
                Text(formatDate(item.item.date))
            }

            Column(Modifier.padding(8.dp)) {
                Text("Qty:${item.item.qty}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(4.dp))
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        onCheckedChange.invoke(item.item,it)
                    },
                )
            }
        }
    }
}


fun formatDate(date: Date):String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)