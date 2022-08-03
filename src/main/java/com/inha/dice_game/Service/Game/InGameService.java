package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.Game.GameDTOCollection;

import java.util.ArrayList;
import java.util.Random;

public interface InGameService {

    void Init(String roomCode);
    long ConvertStringToLong(String stringToConvert);
    ArrayList<Integer> RollDices(ArrayList<Integer> original, int rollAmount, Random random);
    void reRoll(GameDTOCollection.reRoll reRoll);
    void calcPickAvailability(GameDTOCollection.Progress progress);
    void pick(GameDTOCollection.picked picked);
}
