package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.DTO.GameInfoVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class GameListServiceImpl implements GameListService {

    @Autowired
    HashMap<String, GameDTO> GameRoomList;

    @Autowired
    Random randomGenerator;

    @Override
    public ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll) {
        for (Integer i : whichToRoll) {
            dices.set(i,randomGenerator.nextInt(5) + 1);
        }
        return dices;
    }

    @Override
    public ArrayList<GameInfoVO> fetchRooms() {
        ArrayList<GameInfoVO> returnArray = new ArrayList<>(GameRoomList.size());
        for(GameDTO gameDTO : GameRoomList.values())
        {
            returnArray.add(gameDTO.extractInfo());
        }
        return returnArray;
    }

    @Override
    public boolean deleteRoom(String roomCode) {
        return GameRoomList.remove(roomCode) != null;
    }

    @Override
    public boolean makeNewRoom(GameDTO gameDTO) {
        String roomCode;
        int retryLimit = -1;
        do {
            if(retryLimit > 3)
                return false;
            roomCode = RandomStringUtils.randomAlphanumeric(6).toUpperCase();//설마 겹칠까?
            retryLimit++;
        }while(GameRoomList.get(roomCode) != null);

        gameDTO.setRoomCode(roomCode);
        GameRoomList.put(roomCode,gameDTO);
        return true;
    }
}