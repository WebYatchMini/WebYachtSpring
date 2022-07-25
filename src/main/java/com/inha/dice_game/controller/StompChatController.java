package com.inha.dice_game.controller;

import com.inha.dice_game.DTO.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message)
    {
        System.out.println("received message : " + message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomCode(),message);
    }
}
