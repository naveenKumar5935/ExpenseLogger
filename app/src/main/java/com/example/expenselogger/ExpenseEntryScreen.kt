    package com.example.expenselogger

    import android.annotation.SuppressLint
    import android.provider.Settings.Global
    import android.widget.Toast
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.expenselogger.room.Expense
    import com.example.expenselogger.room.ExpenseDatabase
    import com.example.expenselogger.ui.theme.DarkBlackColor
    import com.example.expenselogger.ui.theme.buttonColor
    import com.example.expenselogger.ui.theme.lightWhiteShade
    import com.example.expenselogger.ui.theme.poppinsFontFamily
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.flow.collect
    import kotlinx.coroutines.flow.toList
    import kotlinx.coroutines.launch
    import java.time.LocalDate

    @SuppressLint("MutableCollectionMutableState")
    @Composable
    fun ExpenseEntryScreen(database: ExpenseDatabase) {

        var categoryEntry by remember { mutableStateOf("") }
        var amount by remember { mutableStateOf("") }

        // Observe expenses from the database
        val expenseDB by database.expenseDao().getAllExpenses().collectAsState(initial = emptyList())

        val context = LocalContext.current

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

                    if(categoryEntry.isEmpty() || amount.isEmpty()){
                        Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
                    }else{
                        GlobalScope.launch {
                            database.expenseDao().insertExpense(
                                Expense(categoryEntry,"$$amount",LocalDate.now().toString())
                            )
                        }
                    }

                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(buttonColor),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "Add Expense",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = lightWhiteShade
                )
            }

            val expensesByDate = expenseDB.groupBy { it.date }


            LazyColumn {
            expensesByDate.forEach { (date, expenses) ->

                    item {
                        Text(
                            text = date.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = lightWhiteShade,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                        items(expenses) { expense ->
                            ShowExpenseCard(expense) {
                                GlobalScope.launch {
                                    database.expenseDao().deleteExpenseById(expense.id)
                                }
                            }
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



