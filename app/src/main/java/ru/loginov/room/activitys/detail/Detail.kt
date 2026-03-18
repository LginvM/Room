package ru.loginov.room.activitys.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.loginov.room.ui.Category
import ru.loginov.room.ui.theme.Shapes
import java.util.Date

@Composable
fun DetailScreen(
    id:Int,
    navigateUp:() -> Unit
){
    val viewModel = viewModel<DetailViewModel>(factory = DetailViewModelFactor(id))
    Scaffold {

    }
}

@Composable
private fun DetailEntry(
    modifier: Modifier = Modifier,
    state: DetailState,
    onDateSelected:(Date) -> Unit,
    onStoreChange:(String) -> Unit,
    onItemChange:(String) -> Unit,
    onQtyChange:(String) -> Unit,
    onCategoryChange:(Category) -> Unit,
    onDialogDismissed:(Boolean) -> Unit,
    onSaveStore:() -> Unit,
    updateItem:() -> Unit,
    saveItem:() -> Unit,
    navigateUp: () -> Unit,
){
    var isNewEnabled by remember{
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.padding(16.dp)
    ){
        TextField(
            value = state.item,
            onValueChange = { onItemChange },
            label = {
                Text(text = "Item")
            },
            modifier = Modifier.fillMaxSize(),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.LightGray
            ),
            shape = Shapes.large
        )
        Spacer(modifier= Modifier.Companion.size(12.dp))

        Row {
            TextField(
                value = state.store,
                onValueChange = {
                    if(isNewEnabled) onStoreChange.invoke(it)
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.LightGray
                ),
                shape = Shapes.large,
                label = { Text(text = "Store")},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onDialogDismissed.invoke(!state.isScreenDialogDismissed)
                        }
                    )
                }
            )

            if (!state.isScreenDialogDismissed){
                Popup(
                    onDismissRequest = {
                        onDialogDismissed.invoke(!state.isScreenDialogDismissed)
                    }
                ){
                    Surface(modifier = Modifier.padding(16.dp)){
                        state.StoreList.forEach{
                            Text(
                                text = it.storeName,
                                modifier = Modifier.padding(8.dp)
                                    .clickable{
                                        onStoreChange.invoke(it.storeName)
                                        onDialogDismissed(!state.isScreenDialogDismissed)
                                    }
                            )

                        }
                    }
                }
            }
            TextButton(onClick = {
                isNewEnabled = if (isNewEnabled){
                onSaveStore.invoke()
                !isNewEnabled
            }else{
                !isNewEnabled
            } }
            ) {
                Text(text = if(isNewEnabled) "Save" else "New")
            }

        }
    }
}