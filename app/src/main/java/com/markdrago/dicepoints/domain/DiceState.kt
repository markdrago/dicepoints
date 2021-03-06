package com.markdrago.dicepoints.domain

import com.markdrago.dicepoints.GameConstants

class DiceState(val nextRoll: () -> Int, val diceList: IntArray, val frozenDice: BooleanArray) {

    constructor(nextRoll: () -> Int):
        this(nextRoll, IntArray(GameConstants.DICE_COUNT, {x -> nextRoll()}), BooleanArray(GameConstants.DICE_COUNT, {false}))

    fun toggleFrozenDie(pos: Int): DiceState {
        val newFrozenDice = frozenDice.mapIndexed { i, b -> if (i == pos) !b else b}
        return DiceState(nextRoll, diceList, newFrozenDice.toBooleanArray())
    }

    fun partialRoll(): DiceState {
        val newDice = diceList.mapIndexed { i, x ->
            if(frozenDice[i])
                diceList[i]
            else
                nextRoll()
        }

        return DiceState(nextRoll, newDice.toIntArray(), frozenDice)
    }

    override fun toString(): String {
        return diceList.joinToString()
    }
}
