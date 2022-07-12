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

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDTO message)
    {
        message.setMessage("["+message.getSender() + "]님이 참가하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomCode(),message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message)
    {
        template.convertAndSend("/sub/chat/room/" + message.getRoomCode(),message);
    }
}
