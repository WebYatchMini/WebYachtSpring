package com.inha.dice_game.Service;

import com.inha.dice_game.DTO.LoginVO;
import com.inha.dice_game.entity.Member;

public interface MemberService {

    boolean join(Member member);
    Member login(LoginVO loginVO) throws Exception;
    boolean idCheck(String id);
    boolean nickCheck(String nickname);
    //void withdraw(Member member);

}
