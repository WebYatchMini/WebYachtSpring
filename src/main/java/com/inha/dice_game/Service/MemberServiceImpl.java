package com.inha.dice_game.Service;

import com.inha.dice_game.controller.MemberVO;
import com.inha.dice_game.entity.Member;
import com.inha.dice_game.DAO.JPAMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    JPAMemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean join(Member member)
    {
        if(memberRepository.save(member) != null)
            return true;
        else
            return false;
    }

    @Override
    public Member login(MemberVO memberVO) throws Exception {
        Member temp = memberRepository.findByu_id(memberVO.getId());

        if(temp == null)
            return null;
        if(!passwordEncoder.matches(temp.getPw_hash(),memberVO.getPw()))
            return null;

        return temp;
    }

    @Override
    public boolean idCheck(String id) {
        return memberRepository.existsByuid(id);
    }

    @Override
    public boolean nickCheck(String nickname) {
        return memberRepository.existsBynickname(nickname);
    }
}
