package com.inha.dice_game.member.repository;

import com.inha.dice_game.member.Member;
import com.inha.dice_game.member.repository.MemberRepository;

import java.util.ArrayList;

public class MemMemberRepository implements MemberRepository {

    private static ArrayList<Member> store = new ArrayList<Member>();

    public MemMemberRepository()
    {
        this.store.add(new Member("1","1","123",123,"mouse"));
    }

    @Override
    public void join(Member member) {
        store.add(member);
    }

    @Override
    public Member login(String id, String pw) {

        for (Member m: store)
        {
               if(m.getId().equals(id) && m.getPw().equals(pw))
                   return m;
        }
        return null;
    }
}
