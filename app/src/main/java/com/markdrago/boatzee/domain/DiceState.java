package com.markdrago.boatzee.domain;

import com.markdrago.boatzee.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class DiceState {
    private List<Integer> diceList;
    private List<Boolean> frozenDice;

    public DiceState(List<Integer> diceList) {
        this.diceList = diceList;

        this.frozenDice = new ArrayList<>();
        for (int i = 0; i < GameConstants.DICE_COUNT; i++) {
            this.frozenDice.add(false);
        }
    }

    public DiceState(List<Integer> diceList, List<Boolean> frozenDice) {
        this.diceList = diceList;
        this.frozenDice = frozenDice;
    }

    public Integer nonFrozenDiceCount() {
        int count = 0;
        for (int i = 0; i < GameConstants.DICE_COUNT; i++) {
            if (frozenDice.get(i)) {
                count++;
            }
        }
        return count;
    }

    public DiceState toggleFrozenDie(int position) {
        boolean newState = ! frozenDice.get(position);

        List<Boolean> newFrozenDice = new ArrayList<>(frozenDice);
        newFrozenDice.set(position, newState);
        return new DiceState(diceList, newFrozenDice);
    }
}
