package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.GameDTO;

import java.util.ArrayList;
import java.util.HashMap;


public interface GameListService {

    ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll);
    HashMap<String, GameDTO> fetchRooms();
    boolean deleteRoom(String roomCode);
    boolean makeNewRoom(GameDTO gameDTO);
}
