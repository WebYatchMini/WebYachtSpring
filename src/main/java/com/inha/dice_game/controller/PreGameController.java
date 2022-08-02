package com.inha.dice_game.controller;

import com.inha.dice_game.DTO.Chat.ChatDTOCollection;
import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.Service.Game.GameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class PreGameController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private final GameInfoService gameInfoService;

    @MessageMapping("/pregame/room/readyState")
    public void readyState(GameDTOCollection.ReadyState gameReadyStateDTO)
    {
        gameReadyStateDTO.setType(1);
        simpMessagingTemplate.convertAndSend("/sub/pregame/room/"+gameReadyStateDTO.getRoomCode(),gameReadyStateDTO);
    }


    @MessageMapping("/pregame/room/enter")
    public void enter(ChatDTOCollection.Message message)
    {
        System.out.println("received enter : " + message.getRoomCode());
        gameInfoService.fetchPlayerData(message.getRoomCode());
    }

    @MessageMapping("/pregame/room/start")
    public void prepareGame(GameDTOCollection.Start gameStartDTO)
    {
        System.out.println("received start : " + gameStartDTO.getRoomCode());
        gameStartDTO.setType(2);
        simpMessagingTemplate.convertAndSend("/sub/pregame/room/"+gameStartDTO.getRoomCode(),gameStartDTO);
    }
}
