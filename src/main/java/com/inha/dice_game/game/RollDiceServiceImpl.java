package com.inha.dice_game.game;

import java.util.ArrayList;
import java.util.Random;

public class RollDiceServiceImpl implements RollDiceService{
    @Override
    public ArrayList<Integer> RollDice(ArrayList<Integer> diceList, ArrayList<Integer> whichToRoll) {
        ArrayList<Integer> returndiceList = (ArrayList<Integer>) diceList.clone();
        Random rand = new Random();
        for(int i=0;i<whichToRoll.size();i++)
        {
            returndiceList.set(whichToRoll.indexOf(i),rand.nextInt(6)+1);
        }

        return returndiceList;
    }
}
