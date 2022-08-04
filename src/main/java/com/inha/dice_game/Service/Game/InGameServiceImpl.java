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
        progress.setTotalDices(progress.getDices());
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
            original.add(random.nextInt(6)+1);
        }
        return original;
    }

    @Override
    public void reRoll(GameDTOCollection.reRoll reRoll) {
        System.out.print("Printing received kept Dices: ");
        for(Integer i : reRoll.getKeep())
        {
            System.out.print(i + " ");
        }
        System.out.println();
        GameDTOCollection.Progress progress = gameProgressList.get(reRoll.getRoomCode()); // 진행 정보 로드

        progress.setKeptDices(new ArrayList<>(reRoll.getKeep()));// 클라이언트 저장한 주사위 저장

        ArrayList<Integer> rollResult = RollDices(new ArrayList<>(), reRoll.getRollAmount(), progress.getRandom());//요청 갯수만큼 굴린 결과

        progress.setDices(rollResult);// 보낼 결과 주사위(갯수만큼만)
        reRoll.getKeep().addAll(rollResult);
        progress.setTotalDices(reRoll.getKeep()); // 저장한 주사위 + 굴린 주사위(족보판단용)
        calcPickAvailability(progress);
        progress.setPhase(progress.getPhase() + 1);

        System.out.print("Printing kept Dices: ");
        for(Integer i : progress.getKeptDices())
        {
            System.out.print(i + " ");
        }
        System.out.println();
        gameProgressList.replace(reRoll.getRoomCode(), progress);
        simpMessagingTemplate.convertAndSend("/sub/game/room/" + reRoll.getRoomCode(),progress);
    }

    @Override
    public void calcPickAvailability(GameDTOCollection.Progress progress) {
        ArrayList<Integer> Dices = progress.getTotalDices();

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

        int choice = one + two * 2 + three * 3 + four * 4 + five * 5 + six * 6;
        boolean doubled = (one == 2 || two == 2 || three == 2 || four == 2 || five == 2 || six == 2);
        boolean triple = (one == 3 || two == 3 || three == 3 || four == 3 || five == 3 || six == 3);
        boolean quadruple = (one == 4 || two == 4 || three == 4 || four == 4 || five == 4 || six == 4);
        boolean quintuple = (one == 5 || two == 5 || three == 5 || four == 5 || five == 5 || six == 5);
        boolean sStraight = ((one == 1 && two == 1 && three == 1 && four == 1) || (two == 1 && three == 1 && four == 1 && five == 1) || (three == 1 && four == 1 && five == 1 && six == 1));
        boolean lStraight = ((one == 1 && two == 1 && three == 1 && four == 1 && five == 1) || (two == 1 && three == 1 && four == 1 && five == 1 && six == 1));

        progress.getPickAvailability().put("Choice", choice);
        progress.getPickAvailability().put("Ones",one);
        progress.getPickAvailability().put("Twos",two * 2);
        progress.getPickAvailability().put("Threes",three * 3);
        progress.getPickAvailability().put("Fours",four * 4);
        progress.getPickAvailability().put("Fives",five * 5);
        progress.getPickAvailability().put("Sixes",six * 6);

        if(sStraight)
            progress.getPickAvailability().put("S.Straight", 15);
        if(lStraight)
            progress.getPickAvailability().put("L.Straight", 30);
        if(quintuple)
            progress.getPickAvailability().put("Yacht", 50);
        if(quadruple || quintuple)
            progress.getPickAvailability().put("4 of a kind", choice);
        if(triple || quadruple || quintuple)
            progress.getPickAvailability().put("3 of a kind",choice);
        if(triple && doubled)
            progress.getPickAvailability().put("Full House",choice);

        progress.setPickAvailabilityScore(new ArrayList<>(progress.getPickAvailability().values()));
    }

    @Override
    public void pick(GameDTOCollection.picked picked) {
        GameDTOCollection.Progress progress = gameProgressList.get(picked.getRoomCode());
        ArrayList<Boolean> playerChosen = picked.getPicked();

        int ownersTurn = progress.getIsOwnersTurn();
        int bonus = 0;
        boolean bonusCalcNeeded = (progress.getPick().get(ownersTurn).get(6) == -1);

        for(int i = 0; i< 14;i++)
        {
            if(playerChosen.get(i))
            {
                if(progress.getPick().get(ownersTurn).get(i) == -1) {
                    int add = progress.getPickAvailability().get(gameConstants.getPicks().get(i));
                    progress.getPick().get(ownersTurn).set(i, add);
                    if(ownersTurn == 0)
                        progress.setP2Sum(add + progress.getP2Sum());
                    else
                        progress.setP1Sum(add + progress.getP1Sum());
                }
            }
            if(bonusCalcNeeded && i < 6)
                bonus += progress.getPick().get(ownersTurn).get(i);
        }
        if(bonus > 62) {
            progress.getPick().get(ownersTurn).set(7, 35);
            if (ownersTurn == 0) {
                progress.setP2Sum(35 + progress.getP2Sum());
            } else {
                progress.setP1Sum(progress.getP1Sum() + 35);
            }
        }

        endTurn(progress, picked.getRoomCode());

        gameProgressList.replace(picked.getRoomCode(), progress);
        simpMessagingTemplate.convertAndSend("/sub/game/room/" + picked.getRoomCode(),progress);
    }

    @Override
    public void endTurn(GameDTOCollection.Progress progress,String roomCode)
    {
        progress.setIsOwnersTurn(progress.getIsOwnersTurn() == 0? 1: 0);
        progress.setPlayedClock(progress.getPlayedClock() + 1);
        if(progress.getPlayedClock() % 2 == 0)
        {
            progress.setTurn(progress.getTurn() + 1);
        }
        progress.setPhase(0);
        progress.initPickAvail();
        progress.setDices(RollDices(new ArrayList<>(),5,progress.getRandom()));
        progress.setTotalDices(progress.getDices());
        calcPickAvailability(progress);
        progress.setKeptDices(new ArrayList<>());
    }

}


