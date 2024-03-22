package com.example.expenselogger.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expenselogger.R
import com.example.expenselogger.ui.theme.DarkBlackColor
import com.example.expenselogger.ui.theme.buttonColor
import com.example.expenselogger.ui.theme.lightWhiteShade
import com.example.expenselogger.ui.theme.poppinsFontFamily

@Composable
fun WelcomeScreen(goToExpenseScreen: ()->Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlackColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "TrackExpenses",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = lightWhiteShade.copy(alpha = 0.6f))

            Text(text = "Manage your finances effortlessly",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = lightWhiteShade.copy(alpha = 0.6f))
        }

        Image(painter = painterResource(id = R.drawable.image),
            contentDescription ="",
            modifier = Modifier.size(312.dp))

        Button(onClick = { goToExpenseScreen() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(buttonColor),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Get Started",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = lightWhiteShade
            )
        }

    }

}