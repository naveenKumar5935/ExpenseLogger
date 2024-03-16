package com.example.expenselogger

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expenselogger.room.Expense
import com.example.expenselogger.ui.theme.buttonColor
import com.example.expenselogger.ui.theme.lightWhiteShade
import com.example.expenselogger.ui.theme.poppinsFontFamily

@Composable
fun ShowExpenseCard(expense: Expense, deleteItem:()->Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(buttonColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(13.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(

            ) {
                Text(text = expense.category,
                    color = lightWhiteShade,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp)

                Text(text = expense.amount,
                    color = lightWhiteShade,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp)
            }

            Icon(imageVector = Icons.Filled.Delete,
                contentDescription ="",
                tint = Color.White,
                modifier = Modifier.clickable { deleteItem() })
        }

    }
}