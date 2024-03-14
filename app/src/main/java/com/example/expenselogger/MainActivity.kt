package com.example.expenselogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.expenselogger.room.ExpenseDatabase
import com.example.expenselogger.ui.theme.ExpenseLoggerTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: ExpenseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate the Room database
        database = Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java,
            "expense_database"
        ).fallbackToDestructiveMigration().build()

        setContent {
            ExpenseLoggerTheme {
               AppNavigation(database)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseLoggerTheme {

    }
}