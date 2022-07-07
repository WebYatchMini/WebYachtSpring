package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.DTO.GameInfoVO;

import java.util.ArrayList;


public interface GameListService {

    ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll);
    ArrayList<GameInfoVO> fetchRooms();
    boolean deleteRoom(String roomCode);
    boolean makeNewRoom(GameDTO gameDTO);
}
