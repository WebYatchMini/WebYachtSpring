package com.inha.dice_game.controller;

import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.Service.Game.InGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class InGameController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    InGameService inGameService;

    @MessageMapping("/game/room/reroll")
    public void rerollDice(GameDTOCollection.reRoll reRoll)
    {
        System.out.println("received reroll");
        inGameService.reRoll(reRoll);
    }

    @MessageMapping("/game/room/pick")
    public void pick(GameDTOCollection.picked picked)
    {
        inGameService.pick(picked);
    }


}
