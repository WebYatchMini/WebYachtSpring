package com.inha.dice_game.Service;

import com.inha.dice_game.DTO.LoginVO;
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
    public Member login(LoginVO loginVO) throws Exception {
        System.out.println(loginVO.toString());
        System.out.println(loginVO.getId());
        System.out.println(loginVO.getPw());
        System.out.println(memberRepository.findByuid(loginVO.getId()).toString());
        Member temp = memberRepository.findByuid(loginVO.getId());

        if(temp == null) {
            return null;
        }
        if(!passwordEncoder.matches(loginVO.getPw(),temp.getPw_hash()))
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
