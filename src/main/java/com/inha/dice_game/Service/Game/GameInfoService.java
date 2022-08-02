package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.Game.GameDTOCollection;

import java.util.ArrayList;


public interface GameInfoService {

    ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll);
    ArrayList<GameDTOCollection.Info> fetchRooms();
    boolean deleteRoom(String roomCode);
    GameDTOCollection.ActionResult makeNewRoom(GameDTOCollection.Game gameDTO);
    GameDTOCollection.ActionResult joinRoom(GameDTOCollection.Join gameJoinDTO);
    void exitRoom(GameDTOCollection.Exit gameExitDTO);
    void fetchPlayerData(String roomCode);
}
