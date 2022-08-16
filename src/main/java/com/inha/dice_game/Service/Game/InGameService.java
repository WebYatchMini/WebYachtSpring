package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.Game.GameDTOCollection;

import java.util.ArrayList;
import java.util.Random;

public interface InGameService {

    void Init(String roomCode);
    long ConvertStringToLong(String stringToConvert);
    ArrayList<Integer> RollDices(int rollAmount, Random random);
    void HandleReRoll(GameDTOCollection.reRoll reRoll);
    void calcPickAvailability(GameDTOCollection.Progress progress);
    void pick(GameDTOCollection.picked picked);

    void HandleBonus(GameDTOCollection.Progress progress, int ownersTurn, int bonus);

    void endTurn(GameDTOCollection.Progress progress, String roomCode);
    void endGame(GameDTOCollection.Progress progress,String roomCode);

    void timeout(GameDTOCollection.timeout timeout);
}
