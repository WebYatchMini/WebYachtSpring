package com.inha.dice_game.Service.Game;

import com.inha.dice_game.DAO.JPAMemberRepository;
import com.inha.dice_game.DTO.Game.GameDTOCollection;
import com.inha.dice_game.DTO.Member.MemberDTOCollection;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameInfoServiceImpl implements GameInfoService {

    @Autowired
    LinkedHashMap<String, GameDTOCollection.Game> GameInfoList;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    JPAMemberRepository memberRepository;

    @Autowired
    HashMap<String,GameDTOCollection.Progress> gameProgressList;

    @Override
    public ArrayList<GameDTOCollection.Info> fetchRooms() {
        ArrayList<GameDTOCollection.Info> returnArray = new ArrayList<>();
        for(GameDTOCollection.Game gameDTO : GameInfoList.values())
        {
            returnArray.add(gameDTO.extractInfo());
        }
        return returnArray;
    }

    @Override
    public boolean deleteRoom(String roomCode) {
        return GameInfoList.remove(roomCode) != null;
    }

    @Override
    public GameDTOCollection.ActionResult makeNewRoom(GameDTOCollection.Game gameDTO) {
        String roomCode;
        GameDTOCollection.ActionResult resultDTO = new GameDTOCollection.ActionResult();
        int retryLimit = -1;
        do {
            if(retryLimit > 3)
            {
                resultDTO.setResult(false);
                resultDTO.setRoomCode(null);
                return resultDTO;
            }
            roomCode = RandomStringUtils.randomAlphanumeric(6).toUpperCase();//설마 겹칠까?
            retryLimit++;
        }while(GameInfoList.get(roomCode) != null);

        gameDTO.setRoomCode(roomCode);
        GameInfoList.put(roomCode,gameDTO);

        resultDTO.setResult(true);
        resultDTO.setRoomCode(roomCode);

        gameProgressList.put(roomCode,new GameDTOCollection.Progress());
        return resultDTO;
    }

    @Override
    public GameDTOCollection.ActionResult joinRoom(GameDTOCollection.Join gameJoinDTO) {

        GameDTOCollection.Game temp = GameInfoList.get(gameJoinDTO.getRoomCode());
        GameDTOCollection.ActionResult resultDTO = new GameDTOCollection.ActionResult();


        if(temp == null || (temp.isLocked() && !passwordEncoder.matches(gameJoinDTO.getRoomPwd(),temp.getRoomPwd())|| temp.isFull()))//없는 방이거나 비밀번호가 틀렸거나 꽉 찼거나
        {
            resultDTO.setResult(false);
            resultDTO.setRoomCode(null);
            return resultDTO;
        }

        temp.getPlayers().add(gameJoinDTO.getUid());
        temp.setCurPlayerCount(temp.getCurPlayerCount() + 1);
        temp.setFull(true);

        resultDTO.setRoomCode(gameJoinDTO.getRoomCode());
        resultDTO.setResult(true);

        return resultDTO;
    }

    @Override
    public void fetchPlayerData(String roomCode)
    {
        GameDTOCollection.Game temp = GameInfoList.get(roomCode);
        ArrayList<MemberDTOCollection.Profile> userProfileData = new ArrayList<>();
        MemberDTOCollection.ProfileExchange profileExchangeDTO = new MemberDTOCollection.ProfileExchange();
        for(String uid : temp.getPlayers())
        {
            userProfileData.add(new MemberDTOCollection.Profile(memberRepository.findByuid(uid),true));
        }
        System.out.println("sending...");
        profileExchangeDTO.setType(0);
        profileExchangeDTO.setUserProfileData(userProfileData);
        simpMessagingTemplate.convertAndSend("/sub/pregame/room/"+roomCode,profileExchangeDTO);
    }

    @Override
    public void exitRoom(GameDTOCollection.Exit gameExitDTO)
    {
        System.out.println(gameExitDTO.getRoomCode());
        GameDTOCollection.Game temp = GameInfoList.get(gameExitDTO.getRoomCode());
        int playerCount = temp.getCurPlayerCount() - 1;
        temp.getPlayers().remove(gameExitDTO.getUid());
        if(playerCount == 0)
        {
            gameProgressList.remove(gameExitDTO.getRoomCode());
            GameInfoList.remove(gameExitDTO.getRoomCode());
        }
        else
        {
            temp.setCurPlayerCount(playerCount);
            temp.setFull(false);
            if(temp.getOrganizerUid().equals(gameExitDTO.getUid()))
            {
                temp.setOrganizerUid(temp.getPlayers().get(0));
                temp.setOrganizerName(memberRepository.findByuid(temp.getOrganizerUid()).getNickname());
            }
            this.fetchPlayerData(gameExitDTO.getRoomCode());
        }
    }

}
