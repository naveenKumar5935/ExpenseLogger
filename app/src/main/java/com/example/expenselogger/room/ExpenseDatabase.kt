package com.example.expenselogger.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 2)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
