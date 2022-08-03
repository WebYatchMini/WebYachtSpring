package com.inha.dice_game.DTO.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;

public class GameDTOCollection {

    @Getter
    @Setter
    public static class Start
    {
        private int sender;
        private int type;
        private String message;
        private String roomCode;
    }

    @Getter
    @Setter
    public static class ActionResult {
        private boolean result;
        private String roomCode;
    }

    @Getter
    public static class Info
    {
        private final String roomCode;
        private final String title;
        private final boolean isLocked;
        private final String organizerName;
        private final boolean isStarted;
        private final int curPlayerCount;

        public Info(Game gameDTO) {
            this.roomCode = gameDTO.getRoomCode();
            this.title = gameDTO.getTitle();
            this.isLocked = gameDTO.isLocked();
            this.organizerName = gameDTO.getOrganizerName();
            this.isStarted = gameDTO.isStarted();
            this.curPlayerCount = gameDTO.getCurPlayerCount();
        }
    }

    @Getter
    @Setter
    public static class Join {
        private String roomCode;
        private String roomPwd;
        private String uid;
    }

    @Getter
    @Setter
    public static class Progress {
        private int turn;

        private ArrayList<Integer> Dices;
        private LinkedHashMap<String,Integer> PickAvailability;
        private ArrayList<ArrayList<Integer>> Pick;
        @JsonIgnore
        private int p1Sum;
        @JsonIgnore
        private int p2Sum;
        private int isOwnersTurn;

        private int sum;

        @JsonIgnore
        private Random random;

        public Progress()
        {
            this.turn = 0;
            this.Dices = new ArrayList<>(Arrays.asList(0,0,0,0,0));
            this.PickAvailability = new LinkedHashMap<>();
            initPickAvail();
            this.Pick = new ArrayList<>(2);
            this.Pick.add(new ArrayList<>(14));
            this.Pick.add(new ArrayList<>(14));
            this.p1Sum = 0;
            this.p2Sum = 0;
            this.random = new Random();
        }

        public void initPickAvail()
        {
            this.PickAvailability.put("Ones",0);
            this.PickAvailability.put("Twos",0);
            this.PickAvailability.put("Threes",0);
            this.PickAvailability.put("Fours",0);
            this.PickAvailability.put("Fives",0);
            this.PickAvailability.put("Sixes",0);
            this.PickAvailability.put("Bonus",0);
            this.PickAvailability.put("Choice",0);
            this.PickAvailability.put("3 of a kind",0);
            this.PickAvailability.put("4 of a kind",0);
            this.PickAvailability.put("Full House",0);
            this.PickAvailability.put("S.Straight",0);
            this.PickAvailability.put("L.Straight",0);
            this.PickAvailability.put("Yacht",0);
        }



        public void setSeed(long seed)
        {
            this.random.setSeed(seed);
        }

        public Integer nextInt(Integer bound)
        {
            return random.nextInt(bound);
        }

    }

    @Getter
    @Setter
    public static class picked
    {
        private String roomCode;
        private ArrayList<Boolean> picked;
    }

    @Getter
    @Setter
    public static class reRoll
    {
        private ArrayList<Integer> keep;
        private int rollAmount;
        private String roomCode;
    }

    @Getter
    @Setter
    public static class ReadyState {
        private int type;
        private boolean ready;
        private int sender;
        private String roomCode;
    }


    @Getter
    @Setter
    public static class Game {

        private String roomCode;
        private String title;
        private boolean isLocked;
        private String roomPwd = null;
        private String organizerName;
        private boolean isStarted;
        private boolean isFull;
        private int curPlayerCount;
        private ArrayList<String> players;
        private String organizerUid;

        public void putPlayer(String puid)
        {
            players.add(puid);
        }

        public void removePlayer(String puid)
        {
            players.remove(puid);
        }

        public String getPlayerAtIndex(int i)
        {
            return players.get(i);
        }

        public Game(String title, String roomPwd, String organizerName,String uid) {
            this.title = title;
            if(roomPwd != null) {
                this.roomPwd = roomPwd;
                this.isLocked = true;
            }
            this.organizerName = organizerName;
            this.isStarted = false;
            this.isFull = false;
            this.curPlayerCount = 1;
            this.players = new ArrayList<>();
            this.organizerUid = uid;
            players.add(uid);
        }

        public Info extractInfo()
        {
            return new Info(this);
        }
    }

    @Getter
    @Setter
    public static class Exit{
        private String roomCode;
        private String uid;
    }
}
