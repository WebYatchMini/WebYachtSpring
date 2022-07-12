package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.ChatRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class GameChatServiceImpl implements GameChatService{

    @Autowired
    HashMap<String, ChatRoomDTO> chatRoomDTOHashMap;

    public ChatRoomDTO getRoomByCode(String roomCode)
    {
        return chatRoomDTOHashMap.get(roomCode);
    }

    public void createChatRoomDTO(String roomCode)
    {
        chatRoomDTOHashMap.put(roomCode, new ChatRoomDTO(roomCode));
    }
}
