package com.inha.dice_game.Service;

import com.inha.dice_game.entity.Member;
import com.inha.dice_game.repository.JPAMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    JPAMemberRepository memberRepository;


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public List<Member> login(String id, String pw) {
        return memberRepository.findByIdAndPw(id,pw);
    }
}
