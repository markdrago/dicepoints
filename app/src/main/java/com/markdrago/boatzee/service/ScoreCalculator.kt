package com.markdrago.boatzee.service

object ScoreCalculator {
    fun scoreForSingleFace(faceValue: Int, dice: IntArray): Int {
        return dice.filter({ it == faceValue }).sum()
    }
}