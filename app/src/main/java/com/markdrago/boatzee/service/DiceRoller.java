package com.markdrago.boatzee.service;

import com.google.common.base.Optional;
import com.markdrago.boatzee.GameConstants;
import com.markdrago.boatzee.domain.DiceState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoller {
    private Random random;

    public DiceRoller() {
        random = new Random();
    }

    public DiceState rollDice(Optional<DiceState> diceStateOptional) {
        if (diceStateOptional.isPresent()) {
            return partialRoll(diceStateOptional.get());
        } else {
            return fullRoll();
        }
    }

    private DiceState fullRoll() {
        return new DiceState(randomDiceRolls(GameConstants.DICE_COUNT));
    }

    private DiceState partialRoll(DiceState diceState) {
        int diceNeeded = diceState.nonFrozenDiceCount();
        List<Integer> newDice = randomDiceRolls(diceNeeded);

        //TODO: set new dice rolls in diceState and return
    }

    private List<Integer> randomDiceRolls(int count) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            rolls.add(random.nextInt(GameConstants.DICE_SIDES) + 1);
        }
        return rolls;
    }
}
