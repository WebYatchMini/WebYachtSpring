package com.inha.dice_game.Service;

import com.inha.dice_game.DTO.LoginVO;
import com.inha.dice_game.DTO.ProfileDTO;
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
    public ProfileDTO login(LoginVO loginVO) throws Exception {
        Member temp = memberRepository.findByuid(loginVO.getId());

        if(temp == null)
        {
            temp = new Member(null,null,null,null,null);
        }
        ProfileDTO profileDTO = new ProfileDTO(temp,true);

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
