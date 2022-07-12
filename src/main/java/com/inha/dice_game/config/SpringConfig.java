package com.inha.dice_game.config;

import com.inha.dice_game.DTO.ChatRoomDTO;
import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.Service.Game.GameChatService;
import com.inha.dice_game.Service.Game.GameChatServiceImpl;
import com.inha.dice_game.Service.Game.GameInfoService;
import com.inha.dice_game.Service.Game.GameInfoServiceImpl;
import com.inha.dice_game.Service.Member.MemberService;
import com.inha.dice_game.Service.Member.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;


@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl();
    }

    @Bean
    public GameInfoService gameService(){return new GameInfoServiceImpl();}

    @Bean
    public HashMap<String, ChatRoomDTO> chatRoomDTOHashMap() {return new HashMap<>();}

    @Bean
    public Random randomGenerator(){return new Random();}

    @Bean
    public HashMap<String, WebSocketSession> GameRoomList() {return new HashMap<>();}

    @Bean
    public LinkedHashMap<String, GameDTO> GameInfoList(){
        return new LinkedHashMap<>();
    }

    @Bean
    public GameChatService gameChatService(){return new GameChatServiceImpl();}
}
