package com.inha.dice_game.member.repository;

import com.inha.dice_game.member.Member;

import java.util.Map;

public interface MemberRepository {

    public void join(Member member);
    Member login(String id, String pw);
}
