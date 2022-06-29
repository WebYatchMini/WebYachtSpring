package com.inha.dice_game.Service;

import com.inha.dice_game.entity.Member;

import java.util.List;

public interface MemberService {

    void join(Member member);
    List<Member> login(String id, String pw);
    //void withdraw(Member member);

}
