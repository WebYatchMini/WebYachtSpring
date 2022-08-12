package com.inha.dice_game.DTO.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
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
        private ArrayList<Integer> KeptDices;
        @JsonIgnore
        private ArrayList<Integer> TotalDices;
        @JsonIgnore
        private LinkedHashMap<String,Integer> PickAvailability;

        private ArrayList<Integer> PickAvailabilityScore;
        private ArrayList<ArrayList<Integer>> Pick;
        private int p1Sum;
        private int p2Sum;
        private int isOwnersTurn;
        private int phase;

        private int winner;

        private boolean isEnded;

        @JsonIgnore
        private Random random;
        @JsonIgnore
        private int playedClock;

        public Progress()
        {
            this.turn = 1;
            this.Dices = new ArrayList<>();
            this.TotalDices = new ArrayList<>();
            this.KeptDices = new ArrayList<>();
            this.PickAvailability = new LinkedHashMap<>();
            initPickAvail();
            this.Pick = new ArrayList<>(2);
            this.Pick.add(new ArrayList<>(Collections.nCopies(14,-1)));
            this.Pick.add(new ArrayList<>(Collections.nCopies(14,-1)));
            this.Pick.get(0).set(6,0);
            this.Pick.get(1).set(6,0);
            this.p1Sum = 0;
            this.p2Sum = 0;
            this.random = new Random();
            this.phase = 0;
            this.playedClock = 0;
            this.winner = -1;
            this.isEnded = false;
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

        public String notOrganizerFinder()
        {
            ArrayList<String> temp = new ArrayList<>(players);
            temp.remove(organizerUid);
            return temp.get(0);
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
