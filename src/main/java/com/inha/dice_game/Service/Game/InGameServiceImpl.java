package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.constants.GameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InGameServiceImpl implements InGameService{

    @Autowired
    HashMap<String, GameDTOCollection.Progress> gameProgressList;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    GameConstants gameConstants;

    @Override
    public void Init(String roomCode) {
        GameDTOCollection.Progress progress = new GameDTOCollection.Progress();
        long seed = ConvertStringToLong(roomCode);

        progress.setSeed(seed);
        progress.setDices(this.RollDices(new ArrayList<>(), 5,progress.getRandom()));
        progress.setTurn(progress.getTurn() + 1);
        progress.setIsOwnersTurn(progress.nextInt(2));
        calcPickAvailability(progress);
        gameProgressList.put(roomCode,progress);
        simpMessagingTemplate.convertAndSend("/sub/game/room/" + roomCode,progress);
    }

    @Override
    public long ConvertStringToLong(String stringToConvert) {
        ArrayList<String> chars = new ArrayList<>();
        StringBuilder seed = new StringBuilder();
        long returnValue;
        for(int i = 0; i<stringToConvert.length();i++)
        {
            chars.add(Integer.toString(Character.getNumericValue(stringToConvert.charAt(i))));
        }
        for(String s : chars)
            seed.append(s);
        returnValue = Long.parseLong(seed.toString());
        return returnValue;
    }

    @Override
    public ArrayList<Integer> RollDices(ArrayList<Integer> original, int rollAmount, Random random) {
        for(int i = 0; i<rollAmount;i++)
        {
            original.add(random.nextInt(5)+1);
        }
        return original;
    }

    @Override
    public void reRoll(GameDTOCollection.reRoll reRoll) {
        GameDTOCollection.Progress progress = gameProgressList.get(reRoll.getRoomCode());
        reRoll.getKeep().addAll(RollDices(new ArrayList<>(), reRoll.getRollAmount(), progress.getRandom()));
        progress.setDices(reRoll.getKeep());
        calcPickAvailability(progress);
        gameProgressList.replace(reRoll.getRoomCode(), progress);
        simpMessagingTemplate.convertAndSend("/sub/game/room/" + reRoll.getRoomCode(),progress);
    }

    @Override
    public void calcPickAvailability(GameDTOCollection.Progress progress) {
        ArrayList<Integer> Dices = progress.getDices();

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;

        for(int i : Dices)
        {
            if(i == 1)
                one++;
            else if(i == 2)
                two++;
            else if(i == 3)
                three++;
            else if(i == 4)
                four++;
            else if(i == 5)
                five++;
            else if(i == 6)
                six++;
        }

        int choice = one + two * 2 + three * 3 + four * 4 + five * 5 + six * 6 ;
        boolean doubled = (one == 2 || two == 2 || three == 2 || four == 2 || five == 2 || six == 2);
        boolean triple = (one == 3 || two == 3 || three == 3 || four == 3 || five == 3 || six == 3);
        boolean quadruple = (one == 4 || two == 4 || three == 4 || four == 4 || five == 4 || six == 4);
        boolean quintuple = (one == 5 || two == 5 || three == 5 || four == 5 || five == 5 || six == 5);

        progress.getPickAvailability().replace("Choice", choice);
        progress.getPickAvailability().replace("Ones",one);
        progress.getPickAvailability().replace("Twos",two);
        progress.getPickAvailability().replace("Threes",three);
        progress.getPickAvailability().replace("Fours",four);
        progress.getPickAvailability().replace("Fives",five);
        progress.getPickAvailability().replace("Sixes",six);

        if(one == 1 && two == 1 && three == 1 && four == 1 && five == 1)
            progress.getPickAvailability().replace("S.Straight", 30);
        if(two == 1 && three == 1 && four == 1 && five == 1 && six == 1)
            progress.getPickAvailability().replace("L.Straight", 30);
        if(quintuple)
            progress.getPickAvailability().replace("Yacht", 50);
        if(quadruple)
            progress.getPickAvailability().replace("4 of a kind", choice);
        if(triple)
            progress.getPickAvailability().replace("3 of a kind",choice);
        if(triple && doubled)
            progress.getPickAvailability().replace("Full House",choice);
    }

    @Override
    public void pick(GameDTOCollection.picked picked) {
        GameDTOCollection.Progress progress = gameProgressList.get(picked.getRoomCode());
        ArrayList<Boolean> playerChosen = picked.getPicked();
        int turn = progress.getIsOwnersTurn();
        int bonus = 0;
        int sum = 0;
        for(int i = 0; i< 14;i++)
        {
            if(playerChosen.get(i))
            {
                if(progress.getPick().get(turn).get(i) == 0) {
                    int add = progress.getPickAvailability().get(gameConstants.getPicks().get(i));
                    progress.getPick().get(turn).set(i, add);
                    sum += add;
                    if(turn == 0)
                        progress.setP1Sum(sum);
                    else
                        progress.setP2Sum(sum);
                    if(i<7)
                        bonus += add;
                }
            }
        }
        if(bonus > 62)
            progress.getPick().get(turn).set(7,bonus);
        progress.setSum(sum);
        progress.setTurn(turn == 0? 1: 0);

        gameProgressList.replace(picked.getRoomCode(), progress);
        simpMessagingTemplate.convertAndSend("/sub/game/room/" + picked.getRoomCode(),progress);
    }
}


