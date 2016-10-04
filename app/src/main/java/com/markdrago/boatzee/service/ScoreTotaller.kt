package com.markdrago.boatzee.service

import com.markdrago.boatzee.domain.ScoreBox

object ScoreTotaller {
    fun scoreTotal(scoreCard: Map<ScoreBox, Int>): Int {
        return scoreForTopTotal(scoreCard) + scoreForBottomTotal(scoreCard)
    }

    fun scoreForTopSubTotal(scoreCard: Map<ScoreBox, Int>): Int {
        val scores = listOf(
                scoreCard[ScoreBox.ONES],
                scoreCard[ScoreBox.TWOS],
                scoreCard[ScoreBox.THREES],
                scoreCard[ScoreBox.FOURS],
                scoreCard[ScoreBox.FIVES],
                scoreCard[ScoreBox.SIXES])
        return scores.filterNotNull().sum()
    }

    fun scoreForBonus(scoreCard: Map<ScoreBox, Int>): Int {
        if (scoreForTopSubTotal(scoreCard) > 62) {
            return 35
        } else {
            return 0
        }
    }

    fun scoreForTopTotal(scoreCard: Map<ScoreBox, Int>): Int {
        return scoreForTopSubTotal(scoreCard) + scoreForBonus(scoreCard)
    }

    fun scoreForBottomTotal(scoreCard: Map<ScoreBox, Int>): Int {
        val scores = listOf(
                scoreCard[ScoreBox.THREE_OF_A_KIND],
                scoreCard[ScoreBox.FOUR_OF_A_KIND],
                scoreCard[ScoreBox.FULL_HOUSE],
                scoreCard[ScoreBox.SMALL_STRAIGHT],
                scoreCard[ScoreBox.LARGE_STRAIGHT],
                scoreCard[ScoreBox.FIVE_OF_A_KIND],
                scoreCard[ScoreBox.CHANCE])
        return scores.filterNotNull().sum()
    }
}