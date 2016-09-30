package com.markdrago.boatzee.service

import com.markdrago.boatzee.domain.ScoreCard
import com.markdrago.boatzee.domain.ScoreValue

object ScoreKeeper {
    fun recordScoreForSingleFace(scoreCard: ScoreCard, faceValue: Int, dice: IntArray): ScoreCard {
        if (isSingleFaceValueAlreadyFilled(scoreCard, faceValue)) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForSingleFace(faceValue, dice))
            val newFaces = scoreCard.singleFaces.copyOf()
            newFaces.set(faceValue - 1, value)

            return scoreCard.copy(singleFaces = newFaces)
        }
    }

    fun isSingleFaceValueAlreadyFilled(scoreCard: ScoreCard, faceValue: Int): Boolean {
        return scoreCard.singleFaces[faceValue - 1].frozen
    }

    fun recordScoreForThreeOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        if (scoreCard.threeOfAKind.frozen) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForThreeOfAKind(dice))
            return scoreCard.copy(threeOfAKind = value)
        }
    }

    fun recordScoreForFourOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        if (scoreCard.fourOfAKind.frozen) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForFourOfAKind(dice))
            return scoreCard.copy(fourOfAKind = value)
        }
    }

    fun recordScoreForFiveOfAKind(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        if (scoreCard.fiveOfAKind.frozen) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForFiveOfAKind(dice))
            return scoreCard.copy(fiveOfAKind = value)
        }
    }

    fun recordScoreForSmallStraight(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        if (scoreCard.smallStraight.frozen) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForSmallStraight(dice))
            return scoreCard.copy(smallStraight = value)
        }
    }

    fun recordScoreForLargeStraight(scoreCard: ScoreCard, dice: IntArray): ScoreCard {
        if (scoreCard.largeStraight.frozen) {
            return scoreCard
        } else {
            val value = ScoreValue(ScoreCalculator.scoreForLargeStraight(dice))
            return scoreCard.copy(largeStraight = value)
        }
    }
}