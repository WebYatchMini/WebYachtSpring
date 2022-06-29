package com.inha.dice_game.DAO;

import com.inha.dice_game.controller.MemberVO;

public interface MemberDAO {
    void register(MemberVO memberVO) throws Exception;
}
