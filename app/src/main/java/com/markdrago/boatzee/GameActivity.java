package com.markdrago.boatzee;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        random = new Random();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void rollDice(View view) {
        TextView textView = (TextView) findViewById(R.id.roll_results);
        textView.setText(diceRollDescription());
    }

    @NonNull
    private String diceRollDescription() {
        StringBuffer buffer = new StringBuffer();
        String sep = "";

        List<Integer> dice = randomDiceRoll();
        for (int i = 0; i < 5; i++) {
            buffer.append(sep);
            buffer.append(String.valueOf(dice.get(i)));
            sep = ", ";
        }
        return buffer.toString();
    }

    private List<Integer> randomDiceRoll() {
        List<Integer> dice = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            dice.add(random.nextInt(6) + 1);
        }
        return dice;
    }
}
