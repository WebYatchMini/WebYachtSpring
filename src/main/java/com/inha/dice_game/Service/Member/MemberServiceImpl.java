package com.inha.dice_game.Service.Member;

import com.inha.dice_game.DAO.JPAMemberRepository;
import com.inha.dice_game.DTO.Member.MemberDTOCollection;
import com.inha.dice_game.entity.Member;
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
    public boolean join(Member member) throws Exception
    {
        try {
            memberRepository.save(member);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public MemberDTOCollection.Profile login(MemberDTOCollection.LoginVO loginVO) throws Exception {
        Member temp = memberRepository.findByuid(loginVO.getId());

        if(temp == null)
        {
            temp = new Member(null,null,null,null,null);
        }
        MemberDTOCollection.Profile profileDTO = new MemberDTOCollection.Profile(temp,true);

        if(temp.getPw_hash() == null || !passwordEncoder.matches(loginVO.getPw(),temp.getPw_hash())) {
            profileDTO.nullify();
        }
        return profileDTO;
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
