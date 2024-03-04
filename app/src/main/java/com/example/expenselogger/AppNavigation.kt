package com.example.expenselogger


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome_screen") {
        composable("welcome_screen") {
            WelcomeScreen(){
                navController.navigate("expense_screen")
            }
        }
        composable("expense_screen") {
            ExpenseEntryScreen()
        }
    }
}
