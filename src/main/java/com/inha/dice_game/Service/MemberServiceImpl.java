package com.inha.dice_game.Service;

import com.inha.dice_game.member.Member;
import com.inha.dice_game.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.join(member);
    }

    @Override
    public Member login(String id, String pw) {
        return memberRepository.login(id,pw);
    }
}
