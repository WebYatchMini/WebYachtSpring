package com.inha.dice_game.Service;

import com.inha.dice_game.member.Member;

public interface MemberService {

    void join(Member member);
    Member login(String id,String pw);
    //void withdraw(Member member);

}
