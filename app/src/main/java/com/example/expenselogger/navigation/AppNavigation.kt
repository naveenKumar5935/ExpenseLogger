package com.example.expenselogger.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expenselogger.screens.ExpenseEntryScreen
import com.example.expenselogger.screens.WelcomeScreen
import com.example.expenselogger.room.ExpenseDatabase
import com.example.expenselogger.sharedpref.PreferenceManager

@Composable
fun AppNavigation(database: ExpenseDatabase) {

    val context = LocalContext.current

    val preferenceManager = remember {
        PreferenceManager(context)
    }
    val data by remember {
        mutableStateOf(preferenceManager.getData("welcome_screen_shown"))
    }

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = if (data) "expense_screen" else "welcome_screen"
    ) {
        composable("welcome_screen") {
            WelcomeScreen(){
                preferenceManager.saveData("welcome_screen_shown",true)
                navController.navigate("expense_screen")
            }
        }
        composable("expense_screen") {
            ExpenseEntryScreen(database)
        }
    }
}
