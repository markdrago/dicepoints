package com.markdrago.boatzee.domain

import com.markdrago.boatzee.GameConstants

class DiceState(val nextRoll: () -> Int, val diceList: IntArray, val frozenDice: BooleanArray) {
    constructor(nextRoll: () -> Int, diceList: IntArray) :
        this(nextRoll, diceList, BooleanArray(GameConstants.DICE_COUNT, {false}))

    constructor(nextRoll: () -> Int):
        this(nextRoll, IntArray(GameConstants.DICE_COUNT, {x -> nextRoll()}))

    fun toggleFrozenDie(pos: Int): DiceState {
        val newFrozenDice = frozenDice.mapIndexed { i, b -> if (i == pos) b else !b}
        return DiceState(nextRoll, diceList, newFrozenDice.toBooleanArray())
    }

    fun partialRoll(): DiceState {
        val newDice = diceList.mapIndexed { i, x ->
            if(frozenDice[i])
                diceList[i]
            else
                nextRoll()
        }

        //all frozen dice are no longer frozen after a roll
        return DiceState(nextRoll, diceList)
    }

    override fun toString(): String {
        return diceList.joinToString()
    }
}