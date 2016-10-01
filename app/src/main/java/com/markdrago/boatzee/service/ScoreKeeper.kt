package com.markdrago.boatzee.service

import com.markdrago.boatzee.domain.ScoreCard

object ScoreKeeper {
    fun recordScoreForSingleFace(scoreCard: ScoreCard, faceValue: Int, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForSingleFace(faceValue, dice)
        val newFaces = scoreCard.singleFaces.copyOf()
        newFaces.set(faceValue - 1, value)

        return scoreCard.copy(singleFaces = newFaces)
    }

    fun recordScoreForThreeOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForThreeOfAKind(dice)
        return scoreCard.copy(threeOfAKind = value)
    }

    fun recordScoreForFourOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForFourOfAKind(dice)
        return scoreCard.copy(fourOfAKind = value)
    }

    fun recordScoreForFiveOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForFiveOfAKind(dice)
        return scoreCard.copy(fiveOfAKind = value)
    }

    fun recordScoreForSmallStraight(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForSmallStraight(dice)
        return scoreCard.copy(smallStraight = value)
    }

    fun recordScoreForLargeStraight(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        val value = ScoreCalculator.scoreForLargeStraight(dice)
        return scoreCard.copy(largeStraight = value)
     }
}