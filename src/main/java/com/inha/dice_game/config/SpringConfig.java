package com.inha.dice_game.config;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.Service.Game.GameListService;
import com.inha.dice_game.Service.Game.GameListServiceImpl;
import com.inha.dice_game.Service.Member.MemberService;
import com.inha.dice_game.Service.Member.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Random;


@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl();
    }

    @Bean
    public GameListService gameService(){return new GameListServiceImpl();}

    @Bean
    public Random randomGenerator(){return new Random();}

    @Bean
    public HashMap<String, GameDTO> GameRoomList(){return new HashMap<>();}
}
