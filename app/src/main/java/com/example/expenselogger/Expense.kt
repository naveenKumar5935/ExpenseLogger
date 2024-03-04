package com.example.expenselogger

data class Expense(
    val amount: Double,
    val category: String,
    val description: String = ""
)
