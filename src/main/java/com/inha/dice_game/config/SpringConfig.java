package com.inha.dice_game.config;

import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.Service.Game.GameListService;
import com.inha.dice_game.Service.Game.GameListServiceImpl;
import com.inha.dice_game.Service.Member.MemberService;
import com.inha.dice_game.Service.Member.MemberServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HashMap<String, GameDTO> GameRoomList(){
        GameDTO gameDTO = new GameDTO("test1",null,"34");
        GameDTO gameDTO2 = new GameDTO("test2","test2","34");
        String roomCode = RandomStringUtils.randomAlphanumeric(6).toUpperCase();//설마 겹칠까?
        String roomCode2 = RandomStringUtils.randomAlphanumeric(6).toUpperCase();//설마 겹칠까?
        HashMap<String, GameDTO> returnHashMap = new HashMap<>();
        returnHashMap.put(roomCode,gameDTO);
        returnHashMap.put(roomCode2,gameDTO2);
        return returnHashMap;
    }
}
