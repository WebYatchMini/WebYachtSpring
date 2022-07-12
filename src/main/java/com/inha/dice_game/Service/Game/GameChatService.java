package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.ChatRoomDTO;

public interface GameChatService {

    ChatRoomDTO getRoomByCode(String roomCode);
    void createChatRoomDTO(String roomCode);
}
