package ru.loginov.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.loginov.room.activitys.HomeScreen
import ru.loginov.room.ui.JetShoppingApplication
import ru.loginov.room.ui.JetShoppingNavigation
import ru.loginov.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        Surface(Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background)
        {
            JetShoppingApp()
        }

        }
    }

    @Composable
    fun JetShoppingApp(modifier: Modifier = Modifier) {
        JetShoppingNavigation()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomTheme {
    }
}