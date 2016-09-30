package com.markdrago.boatzee.service

object ScoreCalculator {
    fun scoreForSingleFace(faceValue: Int, dice: IntArray): Int {
        return dice.filter({ it == faceValue }).sum()
    }

    fun scoreForThreeOfAKind(dice: IntArray): Int {
        return scoreTotalIfMatchingDiceCountAtLeast(3, dice)
    }

    fun scoreForFourOfAKind(dice: IntArray): Int {
        return scoreTotalIfMatchingDiceCountAtLeast(4, dice)
    }

    fun scoreForFiveOfAKind(dice: IntArray): Int {
        if (maxMatchingDiceCount(dice) >= 5) {
            return 50
        } else {
            return 0
        }
    }

    fun scoreForSmallStraight(dice: IntArray): Int {
        if (hasAllDiceInAtLeastOne(arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 3, 4, 5),
                intArrayOf(3, 4, 5, 6)
        ), dice)) {
            return 30
        } else {
            return 0
        }
    }

    fun scoreForLargeStraight(dice: IntArray): Int {
        if (hasAllDiceInAtLeastOne(arrayOf(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(2, 3, 4, 5, 6)
        ), dice)) {
            return 40
        } else {
            return 0
        }
    }

    fun hasAllDiceInAtLeastOne(required: Array<IntArray>, dice: IntArray): Boolean {
        return required.any({hasAllDiceIn(it, dice)})
    }

    fun hasAllDiceIn(required: IntArray, dice: IntArray): Boolean {
        return required.all({dice.contains(it)})
    }

    fun scoreTotalIfMatchingDiceCountAtLeast(required: Int, dice: IntArray): Int {
        if (maxMatchingDiceCount(dice) >= required) {
            return dice.sum()
        } else {
            return 0
        }
    }

    fun maxMatchingDiceCount(dice: IntArray): Int {
        return dice.groupBy({it}).values.map({it.count()}).max() ?: 0
    }
}