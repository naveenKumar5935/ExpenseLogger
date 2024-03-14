package com.example.expenselogger.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM expense")
     fun getAllExpenses(): Flow<List<Expense>>

     @Query("delete from expense where id = :expenseID")
     suspend fun deleteExpenseById(expenseID: Int)
}
