package com.example.modul1mobiledaducompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerComposeApp()
        }
    }
}

@Composable
fun DiceRollerComposeApp() {
    var dice1Value by remember { mutableIntStateOf(0) }
    var dice2Value by remember { mutableIntStateOf(0) }
    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = getDiceDrawable(dice1Value)),
                    contentDescription = "Dadu Kiri",
                    modifier = Modifier.size(140.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = getDiceDrawable(dice2Value)),
                    contentDescription = "Dadu Kanan",
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Button(
                onClick = {
                    dice1Value = (1..6).random()
                    dice2Value = (1..6).random()

                    if (dice1Value == dice2Value) {
                        message = "Selamat, anda dapat dadu double!"
                    } else {
                        message = "Anda belum beruntung!"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD0BFFF)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Roll",
                    color = Color(0xFF3D2C8D),
                    fontSize = 18.sp
                )
            }
        }

        if (message.isNotEmpty()) {
            Text(
                text = message,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFFF0F0F0))
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

fun getDiceDrawable(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }
}