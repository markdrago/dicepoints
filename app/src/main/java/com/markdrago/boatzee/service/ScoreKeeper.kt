package com.markdrago.boatzee.service

import com.markdrago.boatzee.domain.ScoreCard
import com.markdrago.boatzee.domain.ScoreValue

object ScoreKeeper {
    fun recordScoreForSingleFace(scoreCard: ScoreCard, faceValue: Int, dice: IntArray): ScoreCard {
        if (isSingleFaceValueAlreadyFilled(scoreCard, faceValue)) {
            return scoreCard
        } else {
            val value = ScoreCalculator.scoreForSingleFace(faceValue, dice)
            val scoreValue = ScoreValue(value, false)
            val newFaces = scoreCard.singleFaces.copyOf()
            newFaces.set(faceValue - 1, scoreValue)

            return scoreCard.copy(singleFaces = newFaces)


        }
    }

    fun isSingleFaceValueAlreadyFilled(scoreCard: ScoreCard, faceValue: Int): Boolean {
        return scoreCard.singleFaces[faceValue - 1].frozen
    }
}