package com.example.expenselogger

import androidx.compose.runtime.mutableStateListOf


data class Expenses(
    val category: String,
    val amount:String)

val expenseDataList = mutableStateListOf<Expenses>()