package com.inha.dice_game.Service.Member;

import com.inha.dice_game.DTO.Member.MemberDTOCollection;
import com.inha.dice_game.entity.Member;

public interface MemberService {

    boolean join(Member member) throws Exception;
    MemberDTOCollection.Profile login(MemberDTOCollection.LoginVO loginVO) throws Exception;
    boolean idCheck(String id);
    boolean nickCheck(String nickname);
    //void withdraw(Member member);

}
