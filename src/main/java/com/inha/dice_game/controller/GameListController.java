package com.inha.dice_game.controller;


import com.inha.dice_game.DTO.GameDTO;
import com.inha.dice_game.Service.Game.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.GenericDeclaration;
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
    public HashMap<String, GameDTO> refresh()
    {
        return gameListService.fetchRooms();
    }

    @PostMapping("/delete")
    public boolean delete(String roomCode)
    {
        return gameListService.deleteRoom(roomCode);
    }

    @PostMapping("/make")
    public boolean make(@RequestBody GameDTO gameDTO)
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
