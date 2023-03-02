package com.omnitech.offers

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nagivation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            //HomeScreen(navController)
        }

        composable(NavigationItem.Details.route) {
            var name = it.arguments?.getString("name")
            //DetailsScreen(name)
        }
    }
}
