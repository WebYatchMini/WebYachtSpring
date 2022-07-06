package com.inha.dice_game.controller;

import com.inha.dice_game.DTO.LoginVO;
import com.inha.dice_game.DTO.MemberVO;
import com.inha.dice_game.DTO.ProfileDTO;
import com.inha.dice_game.Service.Member.MemberService;
import com.inha.dice_game.constants.SessionConstants;
import com.inha.dice_game.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("api")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService,PasswordEncoder passwordEncoder)
    {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/search/id/{uid}")
    public boolean checkID(@PathVariable String uid)
    {
        return memberService.idCheck(uid);
    }

    @GetMapping("/search/nickname/{unickname}")
    public boolean checkNick(@PathVariable String unickname)
    {
        return memberService.nickCheck(unickname);
    }

    @PostMapping("/regist")
    public boolean register(@RequestBody MemberVO memberVO) throws Exception {
        String encodedPW = passwordEncoder.encode(memberVO.getPw());
        String encodedANS = passwordEncoder.encode(memberVO.getHint_answer());
        Member member = new Member(memberVO.getId(), memberVO.getHint(), encodedANS,encodedPW, memberVO.getNickname());

        return memberService.join(member);
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @PostMapping("/login")
    public ProfileDTO login(@RequestBody LoginVO loginVO, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        ProfileDTO profileDTO = memberService.login(loginVO);

        if(profileDTO == null)
            return null;

        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.Login_member,profileDTO);

        return profileDTO;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest)
    {
        HttpSession session = httpServletRequest.getSession(false);
          if(session != null)
            session.invalidate();
    }

}
