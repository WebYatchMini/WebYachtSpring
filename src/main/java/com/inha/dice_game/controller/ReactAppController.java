package com.inha.dice_game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReactAppController {

    @RequestMapping(value = {"/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/*/{y:[\\w\\-]+}","/error"})
    public String index(HttpServletRequest request)
    {
        return "forward:/index.html";
    }
}
