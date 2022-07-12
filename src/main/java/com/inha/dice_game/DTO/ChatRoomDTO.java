package com.inha.dice_game.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomCode;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDTO(String roomCode)
    {
        this.roomCode = roomCode;
    }
}
