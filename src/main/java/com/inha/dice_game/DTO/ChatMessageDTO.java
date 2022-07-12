package com.inha.dice_game.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

    private String roomCode;
    private String sender;
    private String message;

}
