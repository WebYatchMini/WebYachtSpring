package com.inha.dice_game.DTO.Chat;

import lombok.Getter;
import lombok.Setter;

public class ChatDTOCollection {

    @Getter
    @Setter
    public static class Message {
        private String roomCode;
        private int sender;
        private String message;
    }
}
