package com.inha.dice_game.game;

import java.util.ArrayList;

public interface RollDiceService {
    ArrayList<Integer> RollDice(ArrayList<Integer> diceList, ArrayList<Integer> whichToRoll);
}
