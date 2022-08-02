package com.inha.dice_game.controller;

import com.inha.dice_game.DTO.Chat.ChatDTOCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    @Autowired
    private final SimpMessagingTemplate template;



    @MessageMapping("/chat/message")
    public void message(ChatDTOCollection.Message message)
    {
        System.out.println("received message : " + message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomCode(),message);
    }
}
