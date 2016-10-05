package com.markdrago.dicepoints.domain

enum class ScoreBox {
    ONES {
        override fun score(dice: IntArray) = scoreForSingleFace(1, dice)
    },
    TWOS  {
        override fun score(dice: IntArray) = scoreForSingleFace(2, dice)
    },
    THREES {
        override fun score(dice: IntArray) = scoreForSingleFace(3, dice)
    },
    FOURS {
        override fun score(dice: IntArray) = scoreForSingleFace(4, dice)
    },
    FIVES {
        override fun score(dice: IntArray) = scoreForSingleFace(5, dice)
    },
    SIXES {
        override fun score(dice: IntArray) = scoreForSingleFace(6, dice)
    },
    THREE_OF_A_KIND {
        override fun score(dice: IntArray) = scoreTotalIfMatchingDiceCountAtLeast(3, dice)
    },
    FOUR_OF_A_KIND {
        override fun score(dice: IntArray) = scoreTotalIfMatchingDiceCountAtLeast(4, dice)
    },
    FULL_HOUSE {
        override fun score(dice: IntArray): Int {
            val diceSet = dice.groupBy({it}).values.map({it.count()}).toSet()
            if (diceSet.equals(setOf(2, 3))) {
                return 25
            } else {
                return 0
            }
        }
    },
    FIVE_OF_A_KIND {
        override fun score(dice: IntArray): Int {
            if (maxMatchingDiceCount(dice) >= 5) {
                return 50
            } else {
                return 0
            }
        }
    },
    SMALL_STRAIGHT {
        override fun score(dice: IntArray): Int {
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
    },
    LARGE_STRAIGHT {
        override fun score(dice: IntArray): Int {
            if (hasAllDiceInAtLeastOne(arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(2, 3, 4, 5, 6)
            ), dice)) {
                return 40
            } else {
                return 0
            }
        }
    },
    CHANCE {
        override fun score(dice: IntArray) = dice.sum()
    };

    abstract fun score(dice: IntArray): Int

    protected fun scoreForSingleFace(faceValue: Int, dice: IntArray): Int {
        return dice.filter({ it == faceValue }).sum()
    }

    protected fun scoreTotalIfMatchingDiceCountAtLeast(required: Int, dice: IntArray): Int {
        if (maxMatchingDiceCount(dice) >= required) {
            return dice.sum()
        } else {
            return 0
        }
    }

    protected fun maxMatchingDiceCount(dice: IntArray): Int {
        return dice.groupBy({it}).values.map({it.count()}).max() ?: 0
    }

    protected fun hasAllDiceInAtLeastOne(required: Array<IntArray>, dice: IntArray): Boolean {
        return required.any({hasAllDiceIn(it, dice)})
    }

    protected fun hasAllDiceIn(required: IntArray, dice: IntArray): Boolean {
        return required.all({dice.contains(it)})
    }
}