package com.inha.dice_game.DTO.Game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

        private ArrayList<Integer> p1Dices;
        private ArrayList<Integer> p2Dices;

        private ArrayList<Integer> p1Skills;
        private ArrayList<Integer> p2Skills;

        private ArrayList<Boolean> p1PickAvailability;
        private ArrayList<Boolean> p2PickAvailability;

        public Progress()
        {
            this.turn = 0;
            this.p1Dices = new ArrayList<>();
            this.p2Dices = new ArrayList<>();
            this.p1Skills = new ArrayList<>();
            this.p2Skills = new ArrayList<>();

            this.p1PickAvailability = new ArrayList<>();
            this.p2PickAvailability = new ArrayList<>();

        }
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
