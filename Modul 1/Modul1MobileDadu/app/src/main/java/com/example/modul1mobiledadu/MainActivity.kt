package com.example.modul1mobiledadu

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class diceRollerXML : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val buttonRoll: Button = findViewById(R.id.buttonRoll)
        val diceLeft: ImageView = findViewById(R.id.DiceLeft)
        val diceRight: ImageView = findViewById(R.id.DiceRight)
        val resultMessage: TextView = findViewById(R.id.resultMessage)


        buttonRoll.setOnClickListener {
            val dice1Value = (1..6).random()
            val dice2Value = (1..6).random()

            diceLeft.setImageResource(getDiceDrawable(dice1Value))
            diceRight.setImageResource(getDiceDrawable(dice2Value))

            if (dice1Value == dice2Value) {
                resultMessage.text = "Selamat, anda dapat dadu double!"
            } else {
                resultMessage.text = "Anda belum beruntung!"
            }
        }
    }

    private fun getDiceDrawable(value: Int): Int {
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
}