package com.example.expenselogger.room

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expenses(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    val category: String,
    val amount:String)

val expenseDataList = mutableStateListOf<Expenses>()