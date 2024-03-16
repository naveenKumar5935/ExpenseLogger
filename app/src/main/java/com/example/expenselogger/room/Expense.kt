package com.example.expenselogger.room

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    val category: String,
    val amount:String,
val date: String){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}

val expenseDataList = mutableStateListOf<Expense>()
