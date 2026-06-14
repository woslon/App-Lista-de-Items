package com.example.my_list_itens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_list_itens.ui.screen.ExportCsvScreen
import com.example.my_list_itens.ui.screen.listScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {

        composable("list") {
            listScreen(
                navController = navController
            )
        }

        composable("export_csv") {
            ExportCsvScreen(
                navController = navController
            )
        }
    }
}