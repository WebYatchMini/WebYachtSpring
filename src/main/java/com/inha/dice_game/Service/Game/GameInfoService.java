package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.DTO.GameInfoVO;
import com.inha.dice_game.DTO.GameJoinDTO;
import com.inha.dice_game.DTO.GameActionResultDTO;

import java.util.ArrayList;


public interface GameInfoService {

    ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll);
    ArrayList<GameInfoVO> fetchRooms();
    boolean deleteRoom(String roomCode);
    GameActionResultDTO makeNewRoom(GameDTO gameDTO);
    GameActionResultDTO joinRoom(GameJoinDTO gameJoinDTO);
    void exitRoom(String roomCode);
}
