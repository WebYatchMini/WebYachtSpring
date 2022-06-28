package com.inha.dice_game.member.controller;

import com.inha.dice_game.Service.MemberService;
import com.inha.dice_game.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String create(MemberForm form)
    {
        Member member = new Member(form.getId(),form.getPw());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login(MemberForm form)
    {

        memberService.login(form.getId(), form.getPw());

        return "redirect:/";
    }
}
