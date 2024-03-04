package com.example.expenselogger.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Expenses::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase(){
    abstract fun expenseDao() : ExpenseDao
}