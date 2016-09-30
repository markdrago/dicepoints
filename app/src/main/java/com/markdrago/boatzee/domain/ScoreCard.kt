package com.markdrago.boatzee.domain

data class ScoreCard(
        val singleFaces: Array<ScoreValue>,
        val threeOfAKind: ScoreValue,
        val fourOfAKind: ScoreValue,
        val fiveOfAKind: ScoreValue,
        val fullHouse: ScoreValue,
        val smallStraight: ScoreValue,
        val largeStraight: ScoreValue,
        val chance: ScoreValue) {
    constructor():
        this(Array<ScoreValue>(6, {ScoreValue()}),
             ScoreValue(), ScoreValue(), ScoreValue(), ScoreValue(),
             ScoreValue(), ScoreValue(), ScoreValue())
}