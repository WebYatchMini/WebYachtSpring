package com.inha.dice_game.config;

import com.inha.dice_game.Service.MemberService;
import com.inha.dice_game.Service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl();
    }
}
