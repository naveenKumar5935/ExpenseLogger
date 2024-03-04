package com.example.expenselogger.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expenses")
    fun getAllExpenses(): List<Expenses>

    @Insert
    fun insertExpense(expenses: Expenses)
}