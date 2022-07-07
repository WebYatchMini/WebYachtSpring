package com.inha.dice_game.controller;


import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.DTO.GameInfoVO;
import com.inha.dice_game.DTO.ProfileDTO;
import com.inha.dice_game.Service.Game.GameListService;
import com.inha.dice_game.constants.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/room")
public class GameListController {

    private final PasswordEncoder passwordEncoder;
    private final GameListService gameListService;

    @Autowired
    public GameListController(GameListService gameListService, PasswordEncoder passwordEncoder)
    {
        this.gameListService = gameListService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/refresh")
    public ArrayList<GameInfoVO> refresh()
    {
        return gameListService.fetchRooms();
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody String roomCode)
    {
        return gameListService.deleteRoom(roomCode);
    }

    @PostMapping("/make")
    public boolean make(@RequestBody GameDTO gameDTO, HttpServletRequest httpServletRequest)
    {
        String encodedPW;
        if(gameDTO.getRoomPwd() != null)
            encodedPW = passwordEncoder.encode(gameDTO.getRoomPwd());
        else
            encodedPW = null;
        gameDTO.setRoomPwd(encodedPW);
        HttpSession session = httpServletRequest.getSession(false);
        ProfileDTO temp = (ProfileDTO)session.getAttribute(SessionConstants.Login_member);
        gameDTO.setOrganizerName(temp.getNickname());
        return gameListService.makeNewRoom(gameDTO);
    }

    @PostMapping("/tmake")
    public boolean tmake(@RequestBody GameDTO gameDTO, HttpServletRequest httpServletRequest)
    {
        String encodedPW;
        if(gameDTO.getRoomPwd() != null)
            encodedPW = passwordEncoder.encode(gameDTO.getRoomPwd());
        else
            encodedPW = null;
        gameDTO.setRoomPwd(encodedPW);
        return gameListService.makeNewRoom(gameDTO);
    }

}
