package com.inha.dice_game;

import com.inha.dice_game.Service.MemberService;
import com.inha.dice_game.Service.MemberServiceImpl;
import com.inha.dice_game.member.repository.MemMemberRepository;
import com.inha.dice_game.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        return new MemMemberRepository();
    }
}
