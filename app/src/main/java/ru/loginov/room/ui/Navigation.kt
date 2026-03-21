package ru.loginov.room.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.loginov.room.activitys.HomeScreen
import ru.loginov.room.activitys.detail.DetailScreen

enum class Routes{
    Home,
    Detail
}

@Composable
fun JetShoppingNavigation(
    navHostController: NavHostController = rememberNavController()
){
    NavHost(navHostController , startDestination = Routes.Home.name){
        composable(route = Routes.Home.name){
            HomeScreen ( {
                id -> navHostController.navigate(route="${Routes.Detail.name}?id=$id")
            } )
        }
        composable(
            route = "${Routes.Detail.name}?id={id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})
        ){
            val id = it.arguments?.getInt("id") ?: -1
            DetailScreen(
                id = id
            ) {
                navHostController.navigateUp()
            }
        }
    }
}