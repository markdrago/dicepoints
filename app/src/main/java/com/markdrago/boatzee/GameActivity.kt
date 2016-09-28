package com.markdrago.boatzee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.markdrago.boatzee.domain.DiceState
import java.util.Random

class GameActivity : AppCompatActivity() {

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun rollDice(view: View) {
        val diceState = DiceState({random.nextInt(GameConstants.DICE_SIDES) + 1})

        val textView = findViewById(R.id.roll_results) as TextView
        textView.text = diceState.toString()
        renderDiceState(diceState)
    }

    fun renderDiceState(diceState: DiceState) {
        val diceBarLayout = findViewById(R.id.dice_bar) as LinearLayout
        diceState.diceList.forEachIndexed { i, value ->
            val imageView = diceBarLayout.getChildAt(i) as ImageView
            val drawable = getDrawable(getDieResource(value))
            imageView.setImageDrawable(drawable)
        }
    }

    fun getDieResource(value: Int): Int {
        return when(value) {
            1 -> R.drawable.die_1
            2 -> R.drawable.die_2
            3 -> R.drawable.die_3
            4 -> R.drawable.die_4
            5 -> R.drawable.die_5
            6 -> R.drawable.die_6
            else -> throw IllegalStateException("unexpected die value < 1 or > 6")
        }
    }
}


//TODO: credits
//dice art by n4 on opengameart.org: http://opengameart.org/content/white-dices