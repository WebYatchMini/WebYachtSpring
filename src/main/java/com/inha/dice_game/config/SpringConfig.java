package com.inha.dice_game.config;

import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.Service.Game.GameInfoService;
import com.inha.dice_game.Service.Game.GameInfoServiceImpl;
import com.inha.dice_game.Service.Member.MemberService;
import com.inha.dice_game.Service.Member.MemberServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

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
    public Random randomGenerator(){return new Random();}

    @Bean
    public HashMap<String, WebSocketSession> GameRoomList() {return new HashMap<>();}

    @Bean
    public LinkedHashMap<String, GameDTOCollection.Game> GameInfoList(){
        return new LinkedHashMap<>();
    }

    @Bean
    public HashMap<String, GameDTOCollection.Progress> GameProgressList(){return new HashMap<>();}

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebSocketMessageBrokerStats) {
                    WebSocketMessageBrokerStats webSocketMessageBrokerStats = (WebSocketMessageBrokerStats) bean;
                    webSocketMessageBrokerStats.setLoggingPeriod(5 * 1000); // your customization
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
    }
}
