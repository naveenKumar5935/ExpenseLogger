package com.example.expenselogger

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.expenselogger.navigation.AppNavigation
import com.example.expenselogger.room.ExpenseDatabase
import com.example.expenselogger.ui.theme.ExpenseLoggerTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: ExpenseDatabase
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseLoggerTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                        icon = {
                      Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                        })

                    NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                        icon = {
                            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                        })

                    NavigationBarItem(selected = false, onClick = { /*TODO*/ },
                        icon = {
                            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                        })
                }
            }
        ) {

        }
    }
}