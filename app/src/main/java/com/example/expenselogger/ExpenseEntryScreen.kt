package com.example.expenselogger

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expenselogger.ui.theme.DarkBlackColor
import com.example.expenselogger.ui.theme.buttonColor
import com.example.expenselogger.ui.theme.lightWhiteShade
import com.example.expenselogger.ui.theme.poppinsFontFamily

@SuppressLint("MutableCollectionMutableState")
@Preview
@Composable
fun ExpenseEntryScreen() {
    var expenseList by remember{ mutableStateOf(expenseDataList) }
    var categoryEntry by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlackColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(buttonColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Expense Entry",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = lightWhiteShade.copy(alpha = 0.8f)
            )
        }

        // Pass expenseEntry and amount as parameters to TextFields composable
        TextFields(
            expenseEntry = categoryEntry,
            onExpenseEntryChange = { categoryEntry = it },
            amount = amount,
            onAmountChange = { amount = it }
        )

        Button(
            onClick = {
               expenseList.add(Expenses(categoryEntry,amount))
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Submit",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        LazyColumn {

                items(expenseList){
                    ShowExpenseCard(category = it.category, amount = it.amount) {
                        expenseList.remove(it)
                    }
                }


        }
    }
}

@Composable
fun TextFields(
    expenseEntry: String,
    onExpenseEntryChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit
) {
    Column {
        Text(text = "Expense Category", color = lightWhiteShade)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = expenseEntry,
            onValueChange = { onExpenseEntryChange(it) },
            placeholder = { Text("Enter category", color = lightWhiteShade.copy(alpha = 0.5f), fontFamily = poppinsFontFamily) },
            textStyle = TextStyle(color = lightWhiteShade, fontSize = 18.sp, fontFamily = poppinsFontFamily),
            modifier = Modifier.height(56.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Amount", color = lightWhiteShade)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { onAmountChange(it) },
            placeholder = { Text("Enter category", color = lightWhiteShade.copy(alpha = 0.5f), fontFamily = poppinsFontFamily) },
            textStyle = TextStyle(color = lightWhiteShade, fontSize = 18.sp, fontFamily = poppinsFontFamily),
            modifier = Modifier.height(56.dp)
        )
    }
}


