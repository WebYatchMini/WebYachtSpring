package com.inha.dice_game.member.controller;

import com.inha.dice_game.Service.MemberService;
import com.inha.dice_game.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @PostMapping("/member/new")
    public String create(MemberVO form)
    {
        Member member = new Member(form.getId(),form.getPw());
        memberService.join(member);

        return "redirect:/";
    }


    @PostMapping("/login")
    public MemberVO login(@RequestBody MemberVO memberVO)
    {
        MemberVO returnVO = new MemberVO();

        System.out.println("id :"+memberVO.getId()+" PW: " + memberVO.getPw());
        Member member = memberService.login(memberVO.getId(), memberVO.getPw());
        if(member != null)
            returnVO.setNickname(member.getNickname());
        return returnVO;
    }
}
