package com.markdrago.boatzee.domain

data class ScoreCard(
        val singleFaces: IntArray,
        val threeOfAKind: Int,
        val fourOfAKind: Int,
        val fiveOfAKind: Int,
        val fullHouse: Int,
        val smallStraight: Int,
        val largeStraight: Int,
        val chance: Int) {

    constructor(): this(IntArray(6, {0}), 0, 0, 0, 0, 0, 0, 0)
}