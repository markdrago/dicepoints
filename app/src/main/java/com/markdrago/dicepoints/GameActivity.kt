package com.markdrago.dicepoints

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.markdrago.dicepoints.domain.DiceState
import com.markdrago.dicepoints.domain.ScoreBox
import com.markdrago.dicepoints.service.ScoreTotaller
import java.util.*

class GameActivity : AppCompatActivity() {

    private val random = Random()
    private var diceState = DiceState({random.nextInt(GameConstants.DICE_SIDES) + 1})
    private var scoreCard: MutableMap<ScoreBox, Int> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    fun rollDice(view: View) {
        diceState = diceState.partialRoll()
        renderDiceState()
    }

    fun toggleFrozenDie(view: View) {
        diceState = diceState.toggleFrozenDie(getDieViewPosition(view))
        renderDiceState()
    }

    fun recordScore(view: View) {
        val scoreBox = ScoreBox.valueOf(view.tag as String)
        scoreCard[scoreBox] = scoreBox.score(diceState.diceList)
        renderScoreCard()
        renderTotals()
    }

    fun renderDiceState() {
        val diceBarLayout = findViewById(R.id.dice_bar) as LinearLayout
        diceState.diceList.forEachIndexed { i, value ->
            val imageView = diceBarLayout.getChildAt(i) as ImageView
            val drawable = getDrawable(getDieResource(value, diceState.frozenDice[i]))
            imageView.setImageDrawable(drawable)
        }
    }

    fun renderScoreCard() {
        renderButtonScore(R.id.ones_button, scoreCard[ScoreBox.ONES])
        renderButtonScore(R.id.twos_button, scoreCard[ScoreBox.TWOS])
        renderButtonScore(R.id.threes_button, scoreCard[ScoreBox.THREES])
        renderButtonScore(R.id.fours_button, scoreCard[ScoreBox.FOURS])
        renderButtonScore(R.id.fives_button, scoreCard[ScoreBox.FIVES])
        renderButtonScore(R.id.sixes_button, scoreCard[ScoreBox.SIXES])
        renderButtonScore(R.id.three_of_a_kind_button, scoreCard[ScoreBox.THREE_OF_A_KIND])
        renderButtonScore(R.id.four_of_a_kind_button, scoreCard[ScoreBox.FOUR_OF_A_KIND])
        renderButtonScore(R.id.full_house_button, scoreCard[ScoreBox.FULL_HOUSE])
        renderButtonScore(R.id.five_of_a_kind_button, scoreCard[ScoreBox.FIVE_OF_A_KIND])
        renderButtonScore(R.id.small_straight_button, scoreCard[ScoreBox.SMALL_STRAIGHT])
        renderButtonScore(R.id.large_straight_button, scoreCard[ScoreBox.LARGE_STRAIGHT])
        renderButtonScore(R.id.chance_button, scoreCard[ScoreBox.CHANCE])
        renderButtonScore(R.id.top_sub_total, ScoreTotaller.scoreForTopSubTotal(scoreCard))
        renderButtonScore(R.id.top_bonus, ScoreTotaller.scoreForBonus(scoreCard))
        renderButtonScore(R.id.top_total, ScoreTotaller.scoreForTopTotal(scoreCard))
        renderButtonScore(R.id.bottom_total, ScoreTotaller.scoreForBottomTotal(scoreCard))
    }

    fun renderButtonScore(viewId: Int, score: Int?) {
        val view = findViewById(viewId) as Button
        val label = score?.toString() ?: ""
        view.text = label
    }

    fun renderTotals() {
        val player_score_view = findViewById(R.id.player_score) as TextView
        player_score_view.text = "Your Score: " + ScoreTotaller.scoreTotal(scoreCard).toString()

        val opp_score_view = findViewById(R.id.opp_score) as TextView
        opp_score_view.text = "Their Score: 0"
    }

    fun getDieViewPosition(view: View): Int {
        return Integer.valueOf(view.tag as String)
    }

    fun getDieResource(value: Int, isFrozen: Boolean): Int {
        return if (isFrozen) {
            when (value) {
                1 -> R.drawable.die_1_frozen
                2 -> R.drawable.die_2_frozen
                3 -> R.drawable.die_3_frozen
                4 -> R.drawable.die_4_frozen
                5 -> R.drawable.die_5_frozen
                6 -> R.drawable.die_6_frozen
                else -> throw IllegalStateException("unexpected die value < 1 or > 6")
            }
        } else {
            when (value) {
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
}


//TODO: credits
//dice art by n4 on opengameart.org: http://opengameart.org/content/white-dices