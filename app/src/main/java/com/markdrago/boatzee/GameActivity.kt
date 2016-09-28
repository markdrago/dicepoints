package com.markdrago.boatzee

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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
        val textView = findViewById(R.id.roll_results) as TextView
        textView.text = DiceState({random.nextInt(GameConstants.DICE_SIDES) + 1}).toString()
    }
}


//TODO: credits
//dice art by n4 on opengameart.org: http://opengameart.org/content/white-dices