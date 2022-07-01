package com.inha.dice_game.Service;

import com.inha.dice_game.controller.MemberVO;
import com.inha.dice_game.entity.Member;

public interface MemberService {

    boolean join(Member member);
    Member login(MemberVO memberVO) throws Exception;
    boolean idCheck(String id);
    boolean nickCheck(String nickname);
    //void withdraw(Member member);

}
