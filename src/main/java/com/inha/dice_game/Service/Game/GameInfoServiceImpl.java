package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.DTO.GameInfoVO;
import com.inha.dice_game.DTO.GameJoinDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class GameInfoServiceImpl implements GameInfoService {

    @Autowired
    LinkedHashMap<String, GameDTO> GameInfoList;

    @Autowired
    HashMap<String, WebSocketSession> GameRoomList;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Random randomGenerator;

    @Autowired
    GameChatService gameChatService;

    @Override
    public ArrayList<Integer> RollDice(ArrayList<Integer> dices, ArrayList<Integer> whichToRoll) {
        for (Integer i : whichToRoll) {
            dices.set(i,randomGenerator.nextInt(5) + 1);
        }
        return dices;
    }

    @Override
    public ArrayList<GameInfoVO> fetchRooms() {
        ArrayList<GameInfoVO> returnArray = new ArrayList<>(GameInfoList.size());
        for(GameDTO gameDTO : GameInfoList.values())
        {
            returnArray.add(gameDTO.extractInfo());
        }
        return returnArray;
    }

    @Override
    public boolean deleteRoom(String roomCode) {
        return GameInfoList.remove(roomCode) != null;
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
        }while(GameInfoList.get(roomCode) != null);

        gameDTO.setRoomCode(roomCode);
        GameInfoList.put(roomCode,gameDTO);

        gameChatService.createChatRoomDTO(roomCode);
        return true;
    }

    @Override
    public boolean joinRoom(GameJoinDTO gameJoinDTO) {
        GameDTO temp = GameInfoList.get(gameJoinDTO.getRoomCode());
        if(temp.isLocked() && !passwordEncoder.matches(gameJoinDTO.getRoomPwd(),temp.getRoomPwd()))
            return false;
        if(temp.isFull())
            return false;

        temp.setCurPlayerCount(temp.getCurPlayerCount() + 1);
        temp.setFull(true);
        GameInfoList.replace(gameJoinDTO.getRoomCode(),temp);

        return true;
    }

    @Override
    public void exitRoom(String roomCode)
    {
        GameDTO temp = GameInfoList.get(roomCode);
        int playerCount = temp.getCurPlayerCount() - 1;
        if(playerCount == 0)
        {
            GameInfoList.remove(roomCode);
        }
        else
        {
            temp.setCurPlayerCount(playerCount);
            temp.setFull(false);
            GameInfoList.replace(roomCode,temp);
        }
    }

}
